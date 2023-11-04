package com.thealisters.musicquizapp.server.dto;

import lombok.Getter;
import lombok.Setter;

public class GamePostRequestDTO {

    @Getter
    private String userId;
    @Getter
    @Setter
    private String[] songName;

    @Setter
    private String[] songURL;

    @Getter
    @Setter
    private int userScore;

    @Getter
    private int userTimeTaken;

    @Getter
    @Setter
    private int userAtPosition;


}
