package com.thealisters.musicquizapp.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import com.thealisters.musicquizapp.server.service.HighScoreService;
import com.thealisters.musicquizapp.server.service.HighScoreServiceImpl;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest

public class HighScoreControllerTest {
    @Mock
    HighScoreServiceImpl highScoreServiceImpl;

    @InjectMocks
    HighScoreController highScoreController;

    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvcController = MockMvcBuilders.standaloneSetup(highScoreController).build();
        MusicAppTestData.initialiseMusicAppTestData();
    }

    @Test
    public void testGetUserHighScores() throws Exception{
        when(highScoreServiceImpl.getTopUserScoresWithPosition(MusicAppTestData.getHighScoreUserId(),MusicAppTestData.getHighScoreFetchLimit()))
                .thenReturn(Arrays.asList(new UserHighScoreDTO(MusicAppTestData.getHighScoreUserId(), 10, 50, 1),
                        new UserHighScoreDTO(MusicAppTestData.getHighScoreUserId(), 3, 22, 2),
                        new UserHighScoreDTO(MusicAppTestData.getHighScoreUserId(),1,24, 3),
                        new UserHighScoreDTO(MusicAppTestData.getHighScoreUserId(),1, 30, 4),
                        new UserHighScoreDTO(MusicAppTestData.getHighScoreUserId(),1, 32, 5)));

        MvcResult result = mockMvcController.perform(
                        MockMvcRequestBuilders.get("/highscores/user")
                                .param("userId",MusicAppTestData.getHighScoreUserId())
                                .param("limit", MusicAppTestData.getHighScoreFetchLimit()+"")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

}
