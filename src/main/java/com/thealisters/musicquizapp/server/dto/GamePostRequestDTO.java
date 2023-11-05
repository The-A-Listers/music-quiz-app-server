package com.thealisters.musicquizapp.server.dto;

import lombok.Getter;
import lombok.Setter;

public class GamePostRequestDTO {

    @Getter
    @Setter
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
    @Setter
    private int userTimeTaken;

    @Getter
    @Setter
    private int userAtPosition;


}
