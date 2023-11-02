package com.thealisters.musicquizapp.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @GetMapping
    public void requestGame(){

    }

    @PostMapping
    public void postGame(@RequestBody MusicQuizGameDTO musicQuizGameDTO){

    }
}
