package com.thealisters.musicquizapp.server.dto;

import lombok.Getter;

public class GamePostRequestDTO {

    @Getter
    private String userId;
    private String[] correctSongName;
    private String[] correctURL;
    private int userScore;
    private int userTimeTaken;
    private int userAtPosition;


}
