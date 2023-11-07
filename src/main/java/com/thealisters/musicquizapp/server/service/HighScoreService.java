package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.ScoreResponseDTO;
import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HighScoreService {

    List<UserHighScoreDTO> getTopUserScoresWithPosition(String userId, Integer limit);
    List<ScoreResponseDTO> getScoresInDescOrder(int limit);

}
