package com.volleyservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private int matchNumber;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Team> teams;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Set> sets;
    private int numberOfSetsToWinTheMatch;

    public Match(int matchNumber) {
        this.matchNumber = matchNumber;
        this.teams = new ArrayList<>();
        this.sets = new ArrayList<>();
    }

    public Match(int matchNumber, List<Team> teams) {
        this.matchNumber = matchNumber;
        this.teams = teams;
        this.sets = new ArrayList<>();
        this.numberOfSetsToWinTheMatch = 2;
    }

    public Optional<Team> getWinner() {
        return this.sets.stream()
                .map(set -> set.getSetResult().getSetWinner()).flatMap(Optional::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .filter(entry -> entry.getValue() == this.numberOfSetsToWinTheMatch)
                .map(Map.Entry::getKey);
    }

    public Optional<Team> getLoser() {
        return teams.stream()
                .filter(team -> getWinner().isPresent() && !team.equals(getWinner().get()))
                .findFirst();
    }
}
