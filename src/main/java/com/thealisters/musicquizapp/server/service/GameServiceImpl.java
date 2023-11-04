package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import com.thealisters.musicquizapp.server.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    GameScoreRepository gameScoreRepository;
    @Autowired
    SongRepository songRepository;

    @Autowired
    DeezerService deezerService;
    @Override
    public GameGetResponseDTO getGameInputs(int numberOfSongs){
        List<Object[]> songListForSelection = songRepository.getRandomSongNames(numberOfSongs);

        GameGetResponseDTO gameGetResponseDTO = new GameGetResponseDTO();

        String[] correctSongNames = songListForSelection.stream().map(
                objects -> (String) objects[0]+","+(String) objects[1]).toArray(String[]::new);
        gameGetResponseDTO.setCorrectSongNames(correctSongNames);
        String[] songURLForSelection = deezerService.getSongURL(correctSongNames);
        gameGetResponseDTO.setSongURLForSelection(songURLForSelection);

        String[] songNameForSelection = jumbleTheSongNames(Arrays.copyOf(correctSongNames, correctSongNames.length));
        gameGetResponseDTO.setSongNameForSelection(songNameForSelection);
        return gameGetResponseDTO;
    }

    private String[] jumbleTheSongNames(String[] songNamesToJumble){
        Random random = new Random();
        for(int i=songNamesToJumble.length - 1; i > 0; i--){
            int j = random.nextInt(i + 1);
            String temp = songNamesToJumble[i];
            songNamesToJumble[i]=songNamesToJumble[j];
            songNamesToJumble[j]=temp;
        }
        return songNamesToJumble;
    }

    @Override
    public GamePostRequestDTO insertGameResult(GamePostRequestDTO gamePostRequestDTO,
                                               GameGetResponseDTO gameGetResponseDTO){
        int index = 0;
        int score = 0;

        for(String correctSongName : gameGetResponseDTO.getCorrectSongNames()){
            if (correctSongName.equals(gamePostRequestDTO.getSongName()[index])){
                score += 1;
            }
            index += 1;
        }
        GameScore gameScore = new GameScore();
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(gamePostRequestDTO.getUserId());
        gameScore.setUserProfile(userProfile);
        gameScore.setScore(score);
        gameScore.setTime(gamePostRequestDTO.getUserTimeTaken());
        gameScoreRepository.save(gameScore);
        gamePostRequestDTO.setUserAtPosition(
                gameScoreRepository.findPositionByScoreAndTime(
                        score,gamePostRequestDTO.getUserTimeTaken()));
        gamePostRequestDTO.setSongName(gameGetResponseDTO.getSongNameForSelection());
        gamePostRequestDTO.setSongURL(gameGetResponseDTO.getSongURLForSelection());
        gamePostRequestDTO.setUserScore(score);
        return gamePostRequestDTO;
    }
}
