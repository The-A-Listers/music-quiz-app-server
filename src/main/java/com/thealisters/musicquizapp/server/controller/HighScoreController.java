package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.dto.ScoreResponseDTO;
import com.thealisters.musicquizapp.server.dto.UserHighScoreDTO;
import com.thealisters.musicquizapp.server.service.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/highscores")
public class HighScoreController {

    @Autowired
    HighScoreService highScoreService;


    @GetMapping()
    public ResponseEntity<List<ScoreResponseDTO>> getTopScores(@RequestParam(name = "limit", defaultValue="5") int limit){
        return new ResponseEntity<>(highScoreService.getScoresInDescOrder(limit), HttpStatus.OK);
    }

    @GetMapping({"/user"})
    public ResponseEntity<List<UserHighScoreDTO>> getUserHighScores(@RequestParam("userId") String userId,
                                                            @RequestParam("limit") Integer limit){
        List<UserHighScoreDTO> userHighScoreDTO = highScoreService.getTopUserScoresWithPosition(userId, limit);
        return ResponseEntity.ok(userHighScoreDTO);
    }
}
