package com.thealisters.musicquizapp.server.controller;

import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userprofile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfile> getUserProfile(@RequestParam String userId) throws Exception {
        UserProfile userProfile = userProfileService.findById(userId);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @CrossOrigin("http://localhost:5173")
    @PostMapping
    public ResponseEntity<UserProfile> postUserProfile(@RequestBody UserProfile userProfile) throws Exception {
        if (userProfileService.userExists(userProfile.getUserId())) {
            UserProfile existingUser = userProfileService.findById(userProfile.getUserId());
            return ResponseEntity.ok(existingUser);
        }
        UserProfile newUserProfile = userProfileService.insertUserProfile(userProfile);
        return ResponseEntity.ok(newUserProfile);
    }
}