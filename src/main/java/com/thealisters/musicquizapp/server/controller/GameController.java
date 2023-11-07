package com.thealisters.musicquizapp.server.controller;


import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.exception.GameScoreInsertionError;
import com.thealisters.musicquizapp.server.exception.MusicGameNotFoundException;
import com.thealisters.musicquizapp.server.exception.NumberOfSongsNotFoundException;
import com.thealisters.musicquizapp.server.exception.UserIdNotFoundException;
import com.thealisters.musicquizapp.server.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getGame(HttpSession httpSession,@RequestParam int numberOfSongs)
            throws MusicGameNotFoundException, NumberOfSongsNotFoundException {
        if(numberOfSongs <= 0){
            throw new NumberOfSongsNotFoundException("NumberOfSongs parameter is required. Provide a positive number of Songs");
        }
        try {
            GameGetResponseDTO gameGetResponseDTO = gameService.getGameInputs(numberOfSongs);
            if (gameGetResponseDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            JSONObject songsJsonObject = convertGameGetResponseDTOToJSONObject(gameGetResponseDTO);
            httpSession.setAttribute("gameGetResponseDTO",gameGetResponseDTO);
            return ResponseEntity.ok(songsJsonObject.toString());
        }catch(Exception e){
            throw new MusicGameNotFoundException("MusicGameNotFoundException "+e.getMessage());
        }
    }

    private JSONObject convertGameGetResponseDTOToJSONObject(GameGetResponseDTO gameGetResponseDTO){
        JSONObject jsonObject = new JSONObject();
        JSONArray songNameArray = createJSONObject(gameGetResponseDTO.getSongNameForSelection());
        JSONArray songURLArray =  createJSONObject(gameGetResponseDTO.getSongURLForSelection());

        jsonObject.put("songName", songNameArray);
        jsonObject.put("songURL", songURLArray);
        return jsonObject;

    }

    private JSONArray createJSONObject(String[] arrayToConvert){
        JSONArray jsonArray = new JSONArray();
        for(String item : arrayToConvert){
            jsonArray.put(item);
        }
        return jsonArray;
    }

    @PostMapping
    public ResponseEntity<GamePostRequestDTO> postGame(@RequestBody GamePostRequestDTO gamePostRequestDTO, HttpSession session)
                    throws UserIdNotFoundException, GameScoreInsertionError{
        GameGetResponseDTO gameGetResponseDTO = (GameGetResponseDTO) session.getAttribute("gameGetResponseDTO");
        if (gamePostRequestDTO.getUserId() == null || gamePostRequestDTO.getUserId().isEmpty()){
            throw new UserIdNotFoundException("UserId is not passed so cannot insert scores");
        }
        try {
            gamePostRequestDTO = gameService.insertGameResult(gamePostRequestDTO, gameGetResponseDTO);
            HttpHeaders httpHeaders = new HttpHeaders();
            if (gamePostRequestDTO != null)
                httpHeaders.add("musicquizapp", "/api/v1/musicquizapp/" + gamePostRequestDTO.getUserId());
            return new ResponseEntity<>(gamePostRequestDTO, httpHeaders, HttpStatus.CREATED);
        } catch(Exception e){
            throw new GameScoreInsertionError("Error occurred while inserting user game score"+ e.getMessage());
        }
    }
}
