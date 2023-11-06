package com.thealisters.musicquizapp.server.DBUtil;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.thealisters.musicquizapp.server.DBUtil.SongInitialiser;
import com.thealisters.musicquizapp.server.exception.SongInitialisationException;
import com.thealisters.musicquizapp.server.model.Song;
import com.thealisters.musicquizapp.server.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SongInitialiserTest {
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongInitialiser songInitialiser;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_SongInitialiserWhenSongsListTSVExists() throws Exception {
        when(songRepository.count()).thenReturn(0L);
        when(songRepository.save(any(Song.class))).thenReturn(null);
        songInitialiser.run();
        verify(songRepository, times(5000)).save(ArgumentMatchers.any(Song.class));
    }

    @Test
    public void run_SongInitialiserWhenSongsRepositoryIsNotEmpty() throws Exception {
        when(songRepository.count()).thenReturn(5000L);
        songInitialiser.run();
        verify(songRepository,  never()).save(ArgumentMatchers.any(Song.class));
    }

}
