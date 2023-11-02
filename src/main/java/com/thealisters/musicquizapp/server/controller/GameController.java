package com.thealisters.musicquizapp.server.controller;


import org.springframework.web.bind.annotation.*;
import com.thealisters.musicquizapp.server.controller.dto.MusicQuizGameDTO;

@RestController
@RequestMapping("/game")
public class GameController {

    // TODO
    @GetMapping
    public void requestGame(){

    }

    @PostMapping
    public void postGame(@RequestBody MusicQuizGameDTO musicQuizGameDTO){

    }
}
