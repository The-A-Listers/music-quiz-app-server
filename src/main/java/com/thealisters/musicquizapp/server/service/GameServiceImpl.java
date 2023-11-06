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
import java.util.stream.IntStream;

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
        //Only songNames for display to user
        String[] songNames = songListForSelection.stream().map(object -> object[0]).toArray(String[]::new);

        GameGetResponseDTO gameGetResponseDTO = new GameGetResponseDTO();
        gameGetResponseDTO.setCorrectSongNames(songNames);

        //SongNames, Artist name to give search criteria to Deezer API
        String[] correctSongNames = songListForSelection.stream().map(
                objects -> (String) objects[0]+","+(String) objects[1]).toArray(String[]::new);

        //Get songURL from deezer API by sending in songName, artist
        String[] songURLForSelection = deezerService.getSongURL(correctSongNames);
        gameGetResponseDTO.setSongURLForSelection(songURLForSelection);

        //Jumble the songNames to be displayed to user
        String[] songNameForSelection = Arrays.copyOf(songNames, songNames.length);
        jumbleSongNames(songNameForSelection);
        gameGetResponseDTO.setSongNameForSelection(songNameForSelection);
        return gameGetResponseDTO;
    }

    private static void jumbleSongNames(String[] songsToJumble) {
        Arrays.sort(songsToJumble,(s1, s2) -> new Random().nextInt(3) - 1);
    }

    @Override
    public GamePostRequestDTO insertGameResult(GamePostRequestDTO gamePostRequestDTO,
                                               GameGetResponseDTO gameGetResponseDTO){
        int score = (int) IntStream.range(0,gameGetResponseDTO.getCorrectSongNames().length)
                            .filter(i -> gameGetResponseDTO.getCorrectSongNames()[i].equals(gamePostRequestDTO.getSongName()[i]))
                            .count();

        UserProfile userProfile = setUserProfile(gamePostRequestDTO);
        GameScore gameScore = setGameScore(userProfile, gamePostRequestDTO, score);

        gameScoreRepository.save(gameScore);
        gamePostRequestDTO.setUserAtPosition(
                gameScoreRepository.findPositionByScoreAndTime(
                        score,gamePostRequestDTO.getUserTimeTaken()));
        gamePostRequestDTO.setSongName(gameGetResponseDTO.getSongNameForSelection());
        gamePostRequestDTO.setSongURL(gameGetResponseDTO.getSongURLForSelection());
        gamePostRequestDTO.setUserScore(score);
        return gamePostRequestDTO;
    }

    private UserProfile setUserProfile(GamePostRequestDTO gamePostRequestDTO){
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(gamePostRequestDTO.getUserId());
        return userProfile;
    }

    private GameScore setGameScore(UserProfile userProfile, GamePostRequestDTO gamePostRequestDTO, int score){
        GameScore gameScore = new GameScore();
        gameScore.setUserProfile(userProfile);
        gameScore.setScore(score);
        gameScore.setTime(gamePostRequestDTO.getUserTimeTaken());
        return gameScore;
    }
}

