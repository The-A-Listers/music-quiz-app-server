package com.thealisters.musicquizapp.server.repository;

import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.model.Song;
import com.thealisters.musicquizapp.server.model.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SongRepositoryTests {
    @Autowired
    private SongRepository songRepository;

    @Test
    void testFindAll() {
        Song song1 = new Song(1L,15,"this is the end","Sky Fall","Adele","country",true);
        songRepository.save(song1);
        Song song2 = new Song(2L,10,"Test Song","Test title","Ed Sheeran","country",true);
        songRepository.save(song2);

        Iterable<Song> songs = songRepository.findAll();
        assertThat(songs).hasSize(2);
    }

}
