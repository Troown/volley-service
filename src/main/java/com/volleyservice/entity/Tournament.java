package com.volleyservice.entity;

import com.volleyservice.exception.NotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Entity
public class Tournament {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String tournamentName;
    private String city;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Team> registeredTeams;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Round> rounds;


    public Tournament(String tournamentName) {
        this.registeredTeams = new ArrayList<>();
        this.tournamentName = tournamentName;
    }

    public Tournament(String tournamentName, String city) {
        this.registeredTeams = new ArrayList<>();
        this.tournamentName = tournamentName;
        this.city = city;
    }

    public void setTeamsInFirstRound() {

        this.getRounds().get(0).setMatches(List.of(
                new Match(1, List.of(this.registeredTeams.get(0), this.registeredTeams.get(15))),
                new Match(2, List.of(this.registeredTeams.get(8), this.registeredTeams.get(7))),
                new Match(3, List.of(this.registeredTeams.get(4), this.registeredTeams.get(11))),
                new Match(4, List.of(this.registeredTeams.get(12), this.registeredTeams.get(3))),
                new Match(5, List.of(this.registeredTeams.get(2), this.registeredTeams.get(13))),
                new Match(6, List.of(this.registeredTeams.get(10), this.registeredTeams.get(5))),
                new Match(7, List.of(this.registeredTeams.get(6), this.registeredTeams.get(9))),
                new Match(8, List.of(this.registeredTeams.get(14), this.registeredTeams.get(1)))
        ));
    }

    public Optional<Match> findMatchByNumber(Integer matchNumber) {

        return this.rounds.stream()
                .map(Round::getMatches)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .filter(match -> match.getMatchNumber() == matchNumber).findAny();
    }
}
