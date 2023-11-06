package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighScoreServiceImpl implements HighScoreService {

    @Autowired
    GameScoreRepository gameScoreRepository;
    public List<Object[]> getTopUserScoresWithPosition(String userId, Integer limit){
        return gameScoreRepository.getTopUserScoresWithPosition(userId, limit);
    }
}
