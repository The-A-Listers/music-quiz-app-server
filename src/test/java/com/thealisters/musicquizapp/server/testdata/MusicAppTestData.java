package com.thealisters.musicquizapp.server.testdata;

import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import com.thealisters.musicquizapp.server.model.UserProfile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MusicAppTestData {

    @Getter
    private static List<Object[]> mockSongListForSelection = new ArrayList<>();

    @Getter
    private static  GameGetResponseDTO gameGetResponseDTO = null;

    @Getter
    private static  GamePostRequestDTO gamePostRequestDTO = new GamePostRequestDTO();

    @Getter
    private static final String[] mockURLListForSelection = new String[2];

    @Getter
    private static final String[] songName = new String[2];

    @Getter
    private static final int songListLength = 2;

    private static UserProfile userProfile = null;
    @Getter
    private static final String userId = "2000";
    @Getter
    private static final String userName = "Harry Potter";

    @Getter
    private static final int score = 2;

    @Getter
    private static final int time = 35;

    @Getter
    private static final int position = 5;

    @Getter
    private static final long gameId = 200L;

    @Getter
    private static final int numberOfSongs = 2;

    public static void initialiseMusicAppTestData(){
        mockSongListForSelection = Arrays.asList(
                new Object[]{"Song Title1", "Song Artist1"},
                new Object[]{"Song Title2", "Song Artist2"}
        );

        mockURLListForSelection[0] = "URL1";
        mockURLListForSelection[1] = "URL2";

        gameGetResponseDTO =
                new GameGetResponseDTO(songName, mockURLListForSelection,
                        mockSongListForSelection.stream().map((object) -> object[0]+","+object[1]).toArray(String[]::new));

        songName[0] = "Song Title1";
        songName[1] = "Song Title2";

        gameGetResponseDTO.setCorrectSongNames(songName);
        gamePostRequestDTO.setUserId(userId);
        gamePostRequestDTO.setSongName(songName);
        gamePostRequestDTO.setSongURL(mockURLListForSelection);
        gamePostRequestDTO.setUserTimeTaken(time);

        userProfile = new UserProfile(userId, userName, true, false);

   }
}
