package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.service.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/highscores")
public class HighScoreController {

    @Autowired
    HighScoreService highScoreService;


    @GetMapping("/")
    public void getHighScores(){

    }

    @GetMapping({"/user"})
    public List<Object[]> getUserHighScores(@RequestParam("userId") String userId,
                                            @RequestParam("limit") Integer limit){
            return highScoreService.getTopUserScoresWithPosition(userId, limit);
    }
}
