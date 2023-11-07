package com.thealisters.musicquizapp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreResponseDTO {

        private String userName;
        private int score;
        private int time;

}
