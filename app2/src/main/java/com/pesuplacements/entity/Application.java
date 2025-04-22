package com.pesuplacements.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@IdClass(ApplicationId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Application {

    @Id
    private String srn;

    @Id
    private Long jobId;

    private int roundsPassed = 0;

    public int getRoundsPassed() {
        return roundsPassed;
    }

    public void setRoundsPassed(int roundsPassed) {
        this.roundsPassed = roundsPassed;
    }
}
