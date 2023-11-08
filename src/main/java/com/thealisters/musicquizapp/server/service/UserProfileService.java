package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.model.UserProfile;

import java.util.Optional;

public interface UserProfileService {

    UserProfile findById(String userId) throws Exception;

    boolean userExists(String userId);

    UserProfile insertUserProfile(UserProfile userProfile);

}
