package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import com.thealisters.musicquizapp.server.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        String[] songNamesForSelection = songListForSelection.stream().map(
                objects -> (String) objects[0]+","+(String) objects[1]).toArray(String[]::new);
        gameGetResponseDTO.setSongNameForSelection(songNamesForSelection);

        String[] songURLForSelection = deezerService.getSongURL(songNamesForSelection);
        gameGetResponseDTO.setSongURLForSelection(songURLForSelection);
        return gameGetResponseDTO;
    }

    @Override
    public GamePostRequestDTO insertGameResult(GamePostRequestDTO gamePostRequestDTO,
                                               GameGetResponseDTO gameGetResponseDTO){
        int index = 0;
        int score = 0;

        for(String songName : gameGetResponseDTO.getSongNameForSelection()){
            if (songName.equals(gamePostRequestDTO.getCorrectSongName()[index])){
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
        gamePostRequestDTO.setCorrectSongName(gameGetResponseDTO.getSongNameForSelection());
        gamePostRequestDTO.setCorrectURL(gameGetResponseDTO.getSongURLForSelection());
        gamePostRequestDTO.setUserScore(score);
        return gamePostRequestDTO;
    }
}
