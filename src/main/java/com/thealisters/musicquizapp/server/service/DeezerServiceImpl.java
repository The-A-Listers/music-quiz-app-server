package com.thealisters.musicquizapp.server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;

@Service
public class DeezerServiceImpl implements DeezerService{

    @Value("${deezer.api.base-url}")
    private String DEEZER_URL = "https://api.deezer.com/search?q=";

    private final RestTemplate restTemplate;

    public DeezerServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String[] getSongURL(String[] songNamesForSelection){

        ObjectMapper objectMapper = new ObjectMapper();

        String[] deezerSongURL = Arrays.stream(songNamesForSelection)

                .map((songName) -> {
                        try {
                            return restTemplate.getForObject(DEEZER_URL + songName + "&limit=1", String.class);
                        }catch(Exception e){
                            throw new RuntimeException("RestTemplate: "+e.getMessage());
                        }
                })
                .map(json -> {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONArray data = jsonObject.getJSONArray("data");
                        if (data.length() > 0){
                            JSONObject firstResult = data.getJSONObject(0);
                            return firstResult.getString("preview");
                        }else{
                            return null;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("ObjectMapper ReadTree "+e.getMessage());
                    }
                })
                .toArray(String[]::new);

        return deezerSongURL;
    }

}
