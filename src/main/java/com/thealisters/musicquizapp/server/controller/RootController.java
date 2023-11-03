package com.thealisters.musicquizapp.server.controller;


import com.thealisters.musicquizapp.server.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.Generated;


@RestController
@RequestMapping("")
public class RootController {

    private static final String rootContext = "/api/v1/musicquiz";
    @Autowired
    @GetMapping
    public ResponseEntity<String> handleRoot() {
        return ResponseEntity.ok("The AListers Music Quiz API!");
    }

}
