package com.thealisters.musicquizapp.server.dto;

import lombok.Getter;
import lombok.Setter;

public class GameGetResponseDTO {
    @Setter
    @Getter
    private String[] songNameForSelection;

    @Setter
    @Getter
    private String[] songURLForSelection;

    @Setter
    @Getter
    private String[] correctSongNames;

}
