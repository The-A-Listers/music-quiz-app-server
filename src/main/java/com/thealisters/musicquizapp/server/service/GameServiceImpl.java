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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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
        //songNames and songArtistNames for display to user
        String[] songNames = songListForSelection.stream().map(object -> object[0]).toArray(String[]::new);
        String[] songArtistNames = songListForSelection.stream().map(object -> object[1]).toArray(String[]::new);

        //Set correctSongNames and correctSongArtist for display later
        GameGetResponseDTO gameGetResponseDTO = new GameGetResponseDTO();
        gameGetResponseDTO.setCorrectSongNames(songNames);
        gameGetResponseDTO.setCorrectArtist(songArtistNames);

         //SongNames, Artist name to give search criteria to Deezer API
        String[] correctSongNames = songListForSelection.stream().map(
                objects -> (String) objects[0]+","+(String) objects[1]).toArray(String[]::new);

        //Get songURL from deezer API by sending in songName, artist
        String[] songURLForSelection = deezerService.getSongURL(correctSongNames);
        gameGetResponseDTO.setSongURLForSelection(songURLForSelection);

        List<String[]> songDataList = songListForSelection.stream()
                .map(objects -> new String[]{(String) objects[0], (String) objects[1]})
                .collect(Collectors.toList());

        jumbleSongNames(songDataList);
        String[] jumbledSongNames = songDataList.stream()
                .map(data -> data[0])
                .toArray(String[]::new);

        String[] jumbledArtistNames = songDataList.stream()
                .map(data -> data[1])
                .toArray(String[]::new);
        //Jumble the songNames to be displayed to user
        gameGetResponseDTO.setSongNameForSelection(jumbledSongNames);
        gameGetResponseDTO.setSongArtistForSelection(jumbledArtistNames);
        return gameGetResponseDTO;
    }

    private static void jumbleSongNames(List<String[]> songDataList) {
        Collections.shuffle(songDataList, new Random());
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
        gamePostRequestDTO.setSongName(gameGetResponseDTO.getCorrectSongNames());
        gamePostRequestDTO.setSongURL(gameGetResponseDTO.getSongURLForSelection());
        gamePostRequestDTO.setSongArtist(gameGetResponseDTO.getCorrectArtist());
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

