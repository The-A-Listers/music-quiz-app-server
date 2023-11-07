package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.dto.ScoreResponseDTO;
import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import com.thealisters.musicquizapp.server.exception.RecordNotFoundException;
import com.thealisters.musicquizapp.server.exception.RequestParamNotFoundException;
import com.thealisters.musicquizapp.server.service.HighScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/highscores")
public class HighScoreController {

    private static final Logger logger = LoggerFactory.getLogger(HighScoreController.class);

    @Autowired
    HighScoreService highScoreService;


    @GetMapping()
    public ResponseEntity<List<ScoreResponseDTO>> getTopScores(@RequestParam(name = "limit") int limit)
        throws RequestParamNotFoundException, RecordNotFoundException {
        if (limit <= 0) {
            throw new RequestParamNotFoundException("Limit param is not set to limit the number of top high scores of all users");
        }
        try {
            return new ResponseEntity<>(highScoreService.getScoresInDescOrder(limit), HttpStatus.OK);
        }catch(Exception e){
            logger.info("RecordNotFoundException: Could not fetch high scores for all users"+e.getMessage());
            throw new RecordNotFoundException("Error while fetching High Scores of all users");
        }
    }

    @GetMapping({"/user"})
    public ResponseEntity<List<UserHighScoreDTO>> getUserHighScores(@RequestParam("userId") String userId,
                                                            @RequestParam("limit") Integer limit)
                throws RequestParamNotFoundException, RecordNotFoundException{
        if (userId == null || userId.isEmpty()){
            throw new RequestParamNotFoundException("UserId param is not set to fetch top user scores");
        }
        if (limit <= 0){
            throw new RequestParamNotFoundException("Limit param not set to limit the number of top user scores to fetch");
        }
        try {
            List<UserHighScoreDTO> userHighScoreDTO = highScoreService.getTopUserScoresWithPosition(userId, limit);
            return ResponseEntity.ok(userHighScoreDTO);
        }catch(Exception e){
            logger.info("RecordNotFoundException could not find user top scores"+e.getMessage());
            throw new RecordNotFoundException("Error while fetching user top scores");
        }
    }
}
