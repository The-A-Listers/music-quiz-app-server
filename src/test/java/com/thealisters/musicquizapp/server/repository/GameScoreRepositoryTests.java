package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class GameScoreRepositoryTests {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private GameScoreRepository gameScoreRepository;

    @Mock
    private GameScoreRepository mockGameScoreRepository;
    @Mock
    private UserProfileRepository mockUserProfileRepository;

    @Test
    void testFindAll() {
        UserProfile userProfile = new UserProfile("1","amjad",true,false);
        userProfileRepository.save(userProfile);

        GameScore gameScore1 = new GameScore(1L,userProfile,6,119);
        gameScoreRepository.save(gameScore1);
        GameScore gameScore2 = new GameScore(2L,userProfile,9,101);
        gameScoreRepository.save(gameScore2);
        GameScore gameScore3 = new GameScore(3L,userProfile,15,10);
        gameScoreRepository.save(gameScore3);

        Iterable<GameScore> gameScores = gameScoreRepository.findAll();

        assertThat(gameScores).hasSize(3);
    }

    @Test
    public void testFindPositionByScoreAndTime(){
        when(mockGameScoreRepository.findPositionByScoreAndTime(MusicAppTestData.getScore(), MusicAppTestData.getTime()))
                .thenReturn(MusicAppTestData.getPosition());
        assertThat(mockGameScoreRepository.findPositionByScoreAndTime(MusicAppTestData.getScore(),MusicAppTestData.getTime())).isPositive();
    }

    @Test
    public void testGetTopUserScoresWithPosition(){
        when(mockGameScoreRepository.getTopUserScoresWithPosition(MusicAppTestData.getHighScoreUserId(),
                MusicAppTestData.getHighScoreFetchLimit()))
                .thenReturn(Arrays.asList(new Object[]{MusicAppTestData.getHighScoreUserId(), 10, 50, 1},
                        new Object[]{MusicAppTestData.getHighScoreUserId(), 3, 22, 2},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1,24, 3},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 30, 4},
                        new Object[]{MusicAppTestData.getHighScoreUserId(),1, 32, 5}
                ));

        List<Object[]> highScores = mockGameScoreRepository.getTopUserScoresWithPosition(
                MusicAppTestData.getHighScoreUserId(),
                MusicAppTestData.getHighScoreFetchLimit());
        assertEquals(MusicAppTestData.getHighScoreFetchLimit(), highScores.size());
    }

    @Test
    public void testCreateGameScore(){

        UserProfile mockUserProfile = Mockito.mock(UserProfile.class);
        GameScore mockGameScore = Mockito.mock(GameScore.class);

        when(mockGameScoreRepository.findById(MusicAppTestData.getGameId()))
                .thenReturn(Optional.of(mockGameScore));
        mockUserProfileRepository.save(mockUserProfile);
        mockGameScoreRepository.save(mockGameScore);

        Optional<GameScore> savedGameScore = mockGameScoreRepository.findById(MusicAppTestData.getGameId());
        assertThat(savedGameScore).isPresent();
    }
}
