package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HighScoreServiceImpl implements HighScoreService {

    @Autowired
    GameScoreRepository gameScoreRepository;
    public List<UserHighScoreDTO> getTopUserScoresWithPosition(String userId, Integer limit){
        return getTopUserScores(userId, limit);
    }

    public List<UserHighScoreDTO> getTopUserScores(String userId, Integer limit) {
        List<Object[]> dataFromDataSource = gameScoreRepository.getTopUserScoresWithPosition(userId, limit);

        List<UserHighScoreDTO> userHighScoreDTOList = new ArrayList<>();

        for (Object[] tuple : dataFromDataSource) {
            String userIdFromRepo = (String) tuple[0];
            int scoreFromRepo = (int) tuple[1];
            int timeFromRepo = (int) tuple[2];
            long positionFromRepo = (long) tuple[3];

            UserHighScoreDTO userHighScoreDTO = new UserHighScoreDTO(userIdFromRepo, scoreFromRepo, timeFromRepo, positionFromRepo);
            userHighScoreDTOList.add(userHighScoreDTO);
        }

        return userHighScoreDTOList;
    }

}
