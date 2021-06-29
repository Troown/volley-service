package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Round {

    private Phase phase;
    private List<Match> matches;

    public Round(Phase phase, List<List<Team>> teams) {
        this.phase = phase;
        for (List<Team> teamPAir : teams ) {
            this.matches.add(new Match());
        }
    }
}
