package com.thealisters.musicquizapp.server.dto;

import lombok.Getter;
import lombok.Setter;

public class GameGetResponseDTO {
    @Setter
    private String[] songNameForSelection;

    @Setter
    private String[] artistNameForSelection;

    @Setter
    private String[] songURLForSelection;

}
