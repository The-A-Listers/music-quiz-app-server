package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @CrossOrigin("http://localhost:5173")
    @PostMapping
    public ResponseEntity<UserProfile> postUserProfile(@RequestBody UserProfile userProfile){
        UserProfile newUserProfile = userProfileService.insertUserProfile(userProfile);
        return ResponseEntity.ok(newUserProfile);
    }
}