package com.thealisters.musicquizapp.server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController extends RootController{

    @GetMapping("/user")
    public void getUser(){

    }

    @PutMapping("/user")
    public void updateUser(){

    }

    @DeleteMapping("/user")
    public void deleteUser(){

    }


    @GetMapping("/song")
    public void getSong(){

    }

    @PutMapping("/song")
    public void updateSong(){

    }

    @PostMapping("/song")
    public void insertSong(){

    }

    @DeleteMapping("/song")
    public void deleteSong(){

    }
}
