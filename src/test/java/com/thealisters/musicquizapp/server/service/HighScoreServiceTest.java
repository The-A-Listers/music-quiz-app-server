package com.thealisters.musicquizapp.server.service;

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
                .thenReturn(Arrays.asList(new Object[]{MusicAppTestData.getHighScoreUserId(), 10, 50, 1},
                        new Object[]{MusicAppTestData.getHighScoreUserId(), 3, 22, 2},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1,24, 3},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 30, 4},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 32, 5}));
        List<Object[]> highScores = highScoreService.getTopUserScoresWithPosition(MusicAppTestData.getHighScoreUserId(),
                        MusicAppTestData.getHighScoreFetchLimit());
        assertEquals(MusicAppTestData.getHighScoreFetchLimit(), highScores.size());
    }
}
