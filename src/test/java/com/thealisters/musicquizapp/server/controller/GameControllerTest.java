package com.thealisters.musicquizapp.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import com.thealisters.musicquizapp.server.exception.RecordNotFoundException;
import com.thealisters.musicquizapp.server.service.GameServiceImpl;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class GameControllerTest {

    @Mock
    private GameServiceImpl gameServiceImpl;

    @InjectMocks
    private GameController gameController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvcController = MockMvcBuilders.standaloneSetup(gameController).build();
        objectMapper = new ObjectMapper();
        MusicAppTestData.initialiseMusicAppTestData();
    }

    @Test
    void getGame_shouldReturnNotFoundWhenGameNotFound() throws Exception {
        when(gameServiceImpl.getGameInputs(MusicAppTestData.getNumberOfSongs())).thenReturn(null);

        MvcResult result = mockMvcController.perform(
                MockMvcRequestBuilders.get("/game")
                        .param("numberOfSongs",MusicAppTestData.getNumberOfSongs()+"")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getGame_successful() throws RecordNotFoundException, Exception {
        //MockResponse to pass to service
        GameGetResponseDTO mockResponseDTO =
                new GameGetResponseDTO(MusicAppTestData.getSongName(),
                        MusicAppTestData.getMockURLListForSelection(),
                        MusicAppTestData.getSongName());

        //when service called use mockresponseDTO
        when(gameServiceImpl.getGameInputs(MusicAppTestData.getNumberOfSongs())).thenReturn(mockResponseDTO);

        //invoke mockcontroller with param
        MvcResult result = mockMvcController.perform(MockMvcRequestBuilders.get("/game")
                .param("numberOfSongs","2"))
                .andReturn();

        //assert status is ok
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        //check response is returned
        String responseBody = result.getResponse().getContentAsString();
        assertNotNull(responseBody);

        //map the response to GamePostRequestDTO
        ObjectMapper objectMapper = new ObjectMapper();
        GamePostRequestDTO jsonObject = objectMapper.readValue(responseBody, GamePostRequestDTO.class);

        //read JSON values for songName and songURL
        assertEquals(MusicAppTestData.getSongName()[0], jsonObject.getSongName()[0]);
        assertEquals(MusicAppTestData.getSongName()[1], jsonObject.getSongName()[1]);

        assertEquals(MusicAppTestData.getMockURLListForSelection()[0], jsonObject.getSongURL()[0]);
        assertEquals(MusicAppTestData.getMockURLListForSelection()[1], jsonObject.getSongURL()[1]);
    }

    @Test
    void postGame_shouldReturnCreatedWhenGamePosted() throws Exception {

        GameGetResponseDTO gameGetResponseDTO = MusicAppTestData.getGameGetResponseDTO();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("gameGetResponseDTO", gameGetResponseDTO);

        GamePostRequestDTO gamePostRequestDTO = new GamePostRequestDTO();
        gamePostRequestDTO.setUserId(MusicAppTestData.getUserId());
        gamePostRequestDTO.setSongName(MusicAppTestData.getSongName());
        gamePostRequestDTO.setSongURL(MusicAppTestData.getMockURLListForSelection());
        gamePostRequestDTO.setUserTimeTaken(MusicAppTestData.getTime());
        Mockito.when(gameServiceImpl.insertGameResult(gamePostRequestDTO,gameGetResponseDTO)).thenReturn(gamePostRequestDTO);
        this.mockMvcController.perform(MockMvcRequestBuilders.post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                        .content(objectMapper.writeValueAsString(gamePostRequestDTO)))
                        .andExpect(MockMvcResultMatchers.status().isCreated());
   }
}
