package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Tournament {
    private List<BVBTeam> registeredTeams;
    private List<Round> rounds;

    private String tournamentName;
    private String city;

    public Tournament(List<BVBTeam> registeredTeams) {
        this.registeredTeams = registeredTeams;
        this.rounds = List.of(firstRound());
    }

    public Round firstRound() {
        List<BVBTeam> match1Pair = List.of(this.registeredTeams.get(0), this.registeredTeams.get(15));
        List<BVBTeam> match2Pair = List.of(this.registeredTeams.get(8), this.registeredTeams.get(7));
        List<BVBTeam> match3Pair = List.of(this.registeredTeams.get(4), this.registeredTeams.get(11));
        List<BVBTeam> match4Pair = List.of(this.registeredTeams.get(12), this.registeredTeams.get(3));
        List<BVBTeam> match5Pair = List.of(this.registeredTeams.get(2), this.registeredTeams.get(13));
        List<BVBTeam> match6Pair = List.of(this.registeredTeams.get(10), this.registeredTeams.get(5));
        List<BVBTeam> match7Pair = List.of(this.registeredTeams.get(6), this.registeredTeams.get(9));
        List<BVBTeam> match8Pair = List.of(this.registeredTeams.get(14), this.registeredTeams.get(1));
        return new Round(Phase.FIRST_ROUND, List.of(match1Pair, match2Pair, match3Pair, match4Pair, match5Pair, match6Pair, match7Pair, match8Pair));
    }

//    public Round secondRound(Round firstRound) {//co zwrocic jak nie bedzie winnera
//        List<Optional<BVBTeam>> winningTeams = firstRound.getMatches().stream().map(Match::getWinner).collect(Collectors.toList());
//        System.out.println(winningTeams);
//        List<BVBTeam> match9Pair = List.of(firstRound.getMatches().get(0).getWinner().get(), firstRound.getMatches().get(1).getWinner().get());
////        List<BVBTeam> match10Pair = List.of(firstRound.getMatches().get(2).getWinner().get(), firstRound.getMatches().get(3).getWinner().get());
////        List<BVBTeam> match11Pair = List.of(firstRound.getMatches().get(4).getWinner().get(), firstRound.getMatches().get(5).getWinner().get());
////        List<BVBTeam> match12Pair = List.of(firstRound.getMatches().get(6).getWinner().get(), firstRound.getMatches().get(7).getWinner().get());
////        return new Round(Phase.SECOND_ROUND, List.of(match9Pair, match10Pair, match11Pair, match12Pair));
//        return null;
//    }

//    public Round losers13th(Round firstRound) {
//        System.out.println(firstRound.getMatches().stream().map(Match::getLoser).collect(Collectors.toList()));
//        return null;
//    }

}
