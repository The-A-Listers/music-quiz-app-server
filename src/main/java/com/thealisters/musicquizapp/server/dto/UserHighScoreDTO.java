package com.thealisters.musicquizapp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserHighScoreDTO {
    @Getter
    @Setter
    String userId;

    @Getter
    @Setter
    int score;

    @Getter
    @Setter
    int time;

    @Getter
    @Setter
    long position;
}
