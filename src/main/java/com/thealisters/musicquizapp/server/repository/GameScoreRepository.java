package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

    @Query(value="SELECT COUNT(*) + 1 FROM game_score gs WHERE gs.score > :score OR (gs.score = :score AND gs.time < :time)",nativeQuery=true)
    int findPositionByScoreAndTime(@Param("score") Integer score, @Param("time") Integer time);

    @Query(value="SELECT user_id, score, time, RANK() OVER (ORDER BY score DESC, time ASC) AS user_position FROM (\n" +
            "  SELECT user_id, MAX(score) AS score, MIN(time) AS time FROM game_score WHERE user_id = :userId\n" +
            "  GROUP BY user_id, game_id\n" +
            "  ORDER BY score DESC, time ASC\n" +
            "  LIMIT :limit\n" +
            ") AS user_scores", nativeQuery = true)
    List<Object[]> getTopUserScoresWithPosition(@Param("userId") String userId, @Param("limit") int limit);

    @Query(value = "SELECT u.user_name, gs.score, gs.time FROM user_profile u " +
            "JOIN game_score gs ON u.user_id = gs.user_id " +
            "ORDER BY gs.time ASC, gs.score DESC LIMIT :limit",
            nativeQuery = true)
    List<Object[]> findAllScoresInAscTimeOrderThenInDescScoreOrderLimitBy(@Param("limit") Integer limit);
}


