package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    }


