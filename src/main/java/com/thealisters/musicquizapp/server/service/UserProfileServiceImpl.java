package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.model.UserProfile;
import com.thealisters.musicquizapp.server.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserProfile findById(String userId) throws Exception {
        if (userId != null) {
            Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);
            if (userProfileOptional.isPresent()) {
                return userProfileOptional.get();
            }
        }
        throw new Exception("User profile with id " + userId + "not found");
    }

    @Override
    public boolean userExists(String userId) {
        return userProfileRepository.existsById(userId);
    }

    @Override
    public UserProfile insertUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
