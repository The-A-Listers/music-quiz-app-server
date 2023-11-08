package com.thealisters.musicquizapp.server.service;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import com.thealisters.musicquizapp.server.model.GameScore;
import com.thealisters.musicquizapp.server.repository.GameScoreRepository;
import com.thealisters.musicquizapp.server.repository.SongRepository;
import com.thealisters.musicquizapp.server.testdata.MusicAppTestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class GameServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private GameScoreRepository gameScoreRepository;

    @Mock
    private DeezerService deezerService;

    @InjectMocks
    private GameServiceImpl gameServiceImpl;

    @BeforeAll
    public static void setUp(){
        MusicAppTestData.initialiseMusicAppTestData();
    }

    @Test
    public void testGameInputs(){

        when(songRepository.getRandomSongNames(MusicAppTestData.getNumberOfSongs())).thenReturn(MusicAppTestData.getMockSongListForSelection());
        GameGetResponseDTO gameGetResponseDTOInput = gameServiceImpl.getGameInputs(MusicAppTestData.getNumberOfSongs());
        assertEquals(MusicAppTestData.getSongListLength(), gameGetResponseDTOInput.getCorrectSongNames().length);
        assertEquals(MusicAppTestData.getSongListLength(), gameGetResponseDTOInput.getCorrectArtist().length);
        assertEquals(MusicAppTestData.getSongListLength(), gameGetResponseDTOInput.getSongNameForSelection().length);

        assertEquals(MusicAppTestData.getSongName()[0], gameGetResponseDTOInput.getCorrectSongNames()[0]);
        assertEquals(MusicAppTestData.getSongName()[1], gameGetResponseDTOInput.getCorrectSongNames()[1]);

        assertEquals(MusicAppTestData.getSongArtist()[0], gameGetResponseDTOInput.getCorrectArtist()[0]);
        assertEquals(MusicAppTestData.getSongArtist()[1], gameGetResponseDTOInput.getCorrectArtist()[1]);


    }

    @Test
    public void testInsertGameResult(){
        when(gameScoreRepository.findPositionByScoreAndTime(MusicAppTestData.getScore(), MusicAppTestData.getTime())).thenReturn(MusicAppTestData.getPosition());
        GamePostRequestDTO gamePostRequestDTO = gameServiceImpl.insertGameResult(MusicAppTestData.getGamePostRequestDTO(), MusicAppTestData.getGameGetResponseDTO());
        assertEquals(MusicAppTestData.getUserId(), gamePostRequestDTO.getUserId());
        verify(gameScoreRepository, times(1)).save(any(GameScore.class));
    }
}

