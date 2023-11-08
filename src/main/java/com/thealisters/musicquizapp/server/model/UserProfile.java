package com.thealisters.musicquizapp.server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class UserProfile {

        @Id
        @Column(updatable = false, nullable = false)
        String userId;

        @Column
        String userName;

        @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
        boolean adminStatus;

        @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
        boolean banStatus;

}
