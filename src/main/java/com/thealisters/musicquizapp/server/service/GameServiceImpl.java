package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
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
    public GamePostRequestDTO insertGameResult(GamePostRequestDTO gamePostRequestDTO){
        return new GamePostRequestDTO();
    }
}
