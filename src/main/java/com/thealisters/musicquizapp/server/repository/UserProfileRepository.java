package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.UserProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    }


