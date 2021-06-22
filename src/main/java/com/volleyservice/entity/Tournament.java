package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data

public class Tournament {

    private List<Team> registeredTeams;
    private List<Round> rounds;
    private String tournamentName;
    private String city;

    public Tournament(List<Team> registeredTeams) {
        this.registeredTeams = registeredTeams;
        this.rounds = List.of(firstRound());
    }

    public Round firstRound() {
        List<Team> match1Pair = List.of(this.registeredTeams.get(0), this.registeredTeams.get(15));
        List<Team> match2Pair = List.of(this.registeredTeams.get(8), this.registeredTeams.get(7));
        List<Team> match3Pair = List.of(this.registeredTeams.get(4), this.registeredTeams.get(11));
        List<Team> match4Pair = List.of(this.registeredTeams.get(12), this.registeredTeams.get(3));
        List<Team> match5Pair = List.of(this.registeredTeams.get(2), this.registeredTeams.get(13));
        List<Team> match6Pair = List.of(this.registeredTeams.get(10), this.registeredTeams.get(5));
        List<Team> match7Pair = List.of(this.registeredTeams.get(6), this.registeredTeams.get(9));
        List<Team> match8Pair = List.of(this.registeredTeams.get(14), this.registeredTeams.get(1));
        return new Round(Phase.FIRST_ROUND, List.of(match1Pair, match2Pair, match3Pair, match4Pair, match5Pair, match6Pair, match7Pair, match8Pair));
    }
}
