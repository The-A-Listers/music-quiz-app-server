package com.thealisters.musicquizapp.server.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface HighScoreService {

    List<Object[]> getTopUserScoresWithPosition(String userId, Integer limit);

}
