package com.thealisters.musicquizapp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class GamePostRequestDTO {

    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String[] songName;

    @Getter
    @Setter
    private String[] correctSongName;

    @Setter
    @Getter
    private String[] songURL;

    @Setter
    @Getter
    private String[] songArtist;

    @Getter
    @Setter
    private int userScore;

    @Getter
    @Setter
    private int userTimeTaken;

    @Getter
    @Setter
    private int userAtPosition;


}
