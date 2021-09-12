package com.volleyservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Data
@Entity
public class
SetResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    private TeamSetPoint firstTeamSetResult;
    @OneToOne(cascade = {CascadeType.ALL})
    private TeamSetPoint secondTeamSetResult;

    public SetResult(TeamSetPoint firstTeamSetResult, TeamSetPoint secondTeamSetResult) {
        this.firstTeamSetResult = firstTeamSetResult;
        this.secondTeamSetResult = secondTeamSetResult;
    }
}

