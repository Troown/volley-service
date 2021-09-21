package com.volleyservice.entity;

import com.volleyservice.exception.NotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
        this.rounds = new ArrayList<>();
    }

    public Tournament(String tournamentName, String city) {
        this.registeredTeams = new ArrayList<>();
        this.rounds = new ArrayList<>();
        this.tournamentName = tournamentName;
        this.city = city;
    }

    public Optional<Match> findMatchByNumber(Integer matchNumber) {

        return this.rounds.stream()
                .map(Round::getMatches)
                .flatMap(Collection::stream)
                .filter(match -> match.getMatchNumber() == matchNumber).findAny();
    }
}
