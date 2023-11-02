package com.thealisters.musicquizapp.server.controller;


import com.thealisters.musicquizapp.server.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;

@RestController
@RequestMapping("/api/v1/musicquiz")
public class RootController {

    @Autowired

    @GetMapping
    public ResponseEntity<String> handleRoot() {
        return ResponseEntity.ok("The AListers Music Quiz API!");
    }
}
