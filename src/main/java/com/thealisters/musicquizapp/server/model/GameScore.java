package com.thealisters.musicquizapp.server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameScore {

    @GeneratedValue
    @Id
    @Column(updatable = false, nullable = false)
    long gameId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserProfile userProfile;

    @Column
    int score;

    @Column
    int time;
}
