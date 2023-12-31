package com.thealisters.musicquizapp.server.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootController {

    @GetMapping
    public ResponseEntity<String> handleRoot() {
        return ResponseEntity.ok("The AListers Music Quiz API!");
    }
}
