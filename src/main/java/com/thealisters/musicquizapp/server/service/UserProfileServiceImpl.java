package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile geUserProfileById(String userId) {
        return null;
    }

    @Override
    public UserProfile insertUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
