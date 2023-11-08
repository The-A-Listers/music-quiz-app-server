package com.thealisters.musicquizapp.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
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

    @Getter
    @Setter
    private String[] correctArtist;

    @Getter
    @Setter
    private String[] songArtistForSelection;

}
