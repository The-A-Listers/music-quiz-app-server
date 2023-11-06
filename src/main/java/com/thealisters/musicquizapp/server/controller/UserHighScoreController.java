package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.service.HighScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/highscores/user")
public class UserHighScoreController {

    @Autowired
    HighScoreService highScoreService;

    @GetMapping({"/user/{userId}"})
    public void getUserHighScores(@PathVariable("userId") String userId){

    }

}
