package com.thealisters.musicquizapp.server.dto;

import lombok.Setter;

public class GameGetResponseDTO {
    @Setter
    private String[] songNameForSelection;

    @Setter
    private String[] songURLForSelection;

}
