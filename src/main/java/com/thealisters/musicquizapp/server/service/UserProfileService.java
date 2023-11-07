package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.model.UserProfile;

public interface UserProfileService {

    UserProfile geUserProfileById(String userId);

    UserProfile insertUserProfile(UserProfile userProfile);

}
