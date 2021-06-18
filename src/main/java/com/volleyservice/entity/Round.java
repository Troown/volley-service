package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Round {
    private Phase phase;
    private List<Match> matches;

    public Round(Phase phase, List<List<BVBTeam>> teams) {
        this.phase = phase;
        for (List<BVBTeam> teamPAir : teams ) {
            this.matches.add(new Match(teamPAir));
        }
    }
}
