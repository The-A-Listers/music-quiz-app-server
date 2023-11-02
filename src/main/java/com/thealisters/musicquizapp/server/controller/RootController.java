package com.thealisters.musicquizapp.server.controller;


import com.thealisters.musicquizapp.server.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@RestController
@RequestMapping("/api/v1/musicquiz")
public class RootController {

    @Autowired
    RootService rootService;


    @GetMapping("/")
    public void handleRoot(){

    }

    @GetMapping({"/health"})
    public void getHealth(){

    }

}
