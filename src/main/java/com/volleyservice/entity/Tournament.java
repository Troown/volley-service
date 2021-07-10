package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
public class Tournament {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Team> registeredTeams;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Round> rounds;

    private String tournamentName;
    private String city;

    public Tournament(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public Round generateRound1() {
        Match match1 = new Match(1, List.of(this.registeredTeams.get(0), this.registeredTeams.get(15)));
        Match match2 = new Match(2, List.of(this.registeredTeams.get(8), this.registeredTeams.get(7)));
        Match match3 = new Match(3, List.of(this.registeredTeams.get(4), this.registeredTeams.get(11)));
        Match match4 = new Match(4, List.of(this.registeredTeams.get(12), this.registeredTeams.get(3)));
        Match match5 = new Match(5, List.of(this.registeredTeams.get(2), this.registeredTeams.get(13)));
        Match match6 = new Match(6, List.of(this.registeredTeams.get(10), this.registeredTeams.get(5)));
        Match match7 = new Match(7, List.of(this.registeredTeams.get(6), this.registeredTeams.get(9)));
        Match match8 = new Match(8, List.of(this.registeredTeams.get(14), this.registeredTeams.get(1)));
        List<Match> round1Matches = List.of(match1, match2, match3, match4, match5, match6, match7, match8);

        return new Round(1, round1Matches);
    }

    public Round generateRound2(Round round1) {
        Team match1Winner = getMatchWinner(round1, 1);
        Team match2Winner = getMatchWinner(round1, 2);
        Team match3Winner = getMatchWinner(round1, 3);
        Team match4Winner = getMatchWinner(round1, 4);
        Team match5Winner = getMatchWinner(round1, 5);
        Team match6Winner = getMatchWinner(round1, 6);
        Team match7Winner = getMatchWinner(round1, 7);
        Team match8Winner = getMatchWinner(round1, 8);

        Match match9 = new Match(9, List.of(match1Winner, match2Winner));
        Match match10 = new Match(10, List.of(match3Winner, match4Winner));
        Match match11 = new Match(11, List.of(match5Winner, match6Winner));
        Match match12 = new Match(12, List.of(match7Winner, match8Winner));

        List<Match> round2Matches = List.of(match9, match10, match11, match12);

        return new Round(2, round2Matches);
    }

    public Round generatePlace13th(Round round1) {
        Team match1Loser = getMatchLoser(round1, 1);
        Team match2Loser = getMatchLoser(round1, 2);
        Team match3Loser = getMatchLoser(round1, 3);
        Team match4Loser = getMatchLoser(round1, 4);
        Team match5Loser = getMatchLoser(round1, 5);
        Team match6Loser = getMatchLoser(round1, 6);
        Team match7Loser = getMatchLoser(round1, 7);
        Team match8Loser = getMatchLoser(round1, 8);

        Match match13 = new Match(13, List.of(match7Loser, match8Loser));
        Match match14 = new Match(14, List.of(match5Loser, match6Loser));
        Match match15 = new Match(15, List.of(match3Loser, match4Loser));
        Match match16 = new Match(16, List.of(match2Loser, match1Loser));

        List<Match> place13thMatches = List.of(match13, match14, match15, match16);

        return new Round(3, place13thMatches);
    }

    private Team getMatchWinner(Round round, int matchNumber) {

        return round.getMatches().stream().filter(match -> match.getMatchNumber() == matchNumber).findAny().
                orElseThrow(()-> new NoSuchElementException("requested match not found"))
                .getWinner().orElseThrow(()->new NoResultException("the match has not been settled"));
    }

    private Team getMatchLoser(Round round, int matchNumber) {
        return round.getMatches().stream().filter(match -> match.getMatchNumber() == matchNumber).findAny().
                orElseThrow(()-> new NoSuchElementException("requested match not found"))
                .getLoser().orElseThrow(()->new NoResultException("the match has not been settled"));
    }
}
