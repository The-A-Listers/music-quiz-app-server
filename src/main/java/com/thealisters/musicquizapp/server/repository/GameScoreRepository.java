package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

}

