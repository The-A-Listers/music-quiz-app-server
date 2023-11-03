package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "SELECT song_name, song_artist FROM song ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<Object[]> getRandomSongNames(@Param("count") int count);
}


