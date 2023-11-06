package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;

public interface GameService {

    GamePostRequestDTO insertGameResult(GamePostRequestDTO gamePostRequestDTO,
                                        GameGetResponseDTO gameGetResponseDTO);
    GameGetResponseDTO getGameInputs(int numberOfSongs);
}
