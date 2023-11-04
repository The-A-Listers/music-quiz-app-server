package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

    @Query(value="SELECT COUNT(*) + 1 FROM game_score gs WHERE gs.score > :score OR (gs.score = :score AND gs.time < :time)",nativeQuery=true)
    int findPositionByScoreAndTime(@Param("score") Integer score, @Param("time") Integer time);
}


