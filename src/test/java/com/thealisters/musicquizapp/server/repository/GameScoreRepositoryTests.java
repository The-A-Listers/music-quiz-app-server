package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GameScoreRepositoryTests {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private GameScoreRepository gameScoreRepository;
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
        assertThat(gameScoreRepository.findPositionByScoreAndTime(MusicAppTestData.getScore(),MusicAppTestData.getTime())).isPositive();
    }

    @Test
    public void testCreateGameScore(){
        UserProfile userProfile = new UserProfile(MusicAppTestData.getUserId(),MusicAppTestData.getUserName(), true, false);
        userProfileRepository.save(userProfile);
        GameScore gameScore = new GameScore(MusicAppTestData.getGameId(),userProfile,MusicAppTestData.getScore(), MusicAppTestData.getTime());
        gameScoreRepository.save(gameScore);
        Optional<GameScore> savedGameScore = gameScoreRepository.findById(gameScore.getGameId());
        assertThat(savedGameScore).isPresent();
    }

}
