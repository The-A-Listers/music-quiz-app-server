package com.thealisters.musicquizapp.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {

    @GeneratedValue
    @Id
    @Column(updatable = false, nullable = false)
    long songId;

    @Column
    int songRankId;

    @Column
    String songName;

    @Column
    String songTitle;

    @Column
    String songArtist;

    @Column
    String songGenre;

    @Column
    boolean songHealth;
}
