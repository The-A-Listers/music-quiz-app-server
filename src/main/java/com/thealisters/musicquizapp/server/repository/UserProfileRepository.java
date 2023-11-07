package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    @Query("select count(user) = 1 from UserProfile user where user.userId = ?1")
    public boolean existsById(String userId);

}


