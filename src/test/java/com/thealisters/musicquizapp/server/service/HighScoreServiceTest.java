package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DataJpaTest
public class HighScoreServiceTest {
    @Mock
    private GameScoreRepository gameScoreRepository;

    @InjectMocks
    private HighScoreServiceImpl highScoreService;

    @BeforeAll
    public static void setUp(){
        MusicAppTestData.initialiseMusicAppTestData();
    }

    @Test
    public void testGetTopUserScoresWithPosition(){
        when(gameScoreRepository.getTopUserScoresWithPosition(
                MusicAppTestData.getHighScoreUserId(), MusicAppTestData.getHighScoreFetchLimit()))
                .thenReturn(Arrays.asList(new Object[]{MusicAppTestData.getHighScoreUserId(), 10, 50, 1L},
                        new Object[]{MusicAppTestData.getHighScoreUserId(), 3, 22, 2L},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1,24, 3L},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 30, 4L},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 32, 5L}));
        List<UserHighScoreDTO> highScores = highScoreService.getTopUserScoresWithPosition(MusicAppTestData.getHighScoreUserId(),
                        MusicAppTestData.getHighScoreFetchLimit());
        assertEquals(MusicAppTestData.getHighScoreFetchLimit(), highScores.size());
    }
}
