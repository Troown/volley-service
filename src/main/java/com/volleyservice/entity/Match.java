package com.volleyservice.entity;

import jdk.jshell.EvalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Match {
    //TODO UUId
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name ="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private int matchNumber;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Team> teams;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<MatchSet> sets;


    public Match(int matchNumber) {
        this.matchNumber = matchNumber;
        this.teams = new ArrayList<>();
        this.sets = new ArrayList<>();
    }

    public Match(int matchNumber, List<Team> teams) {
        this.matchNumber = matchNumber;
        this.teams = teams;
        this.sets = new ArrayList<>();
    }

    public Optional<Team> getWinner() {
        var result = this.sets.stream()
                .map(matchSet -> matchSet.getSetResult().getWinnerOfSet())
                .collect(Collectors.toList())
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        if (result.isPresent() && result.get().getValue() == 2) {
            return result.get().getKey();

        } else return Optional.empty();

    }

    public Optional<Team> getLoser() {
        var result = this.sets.stream()
                .map(matchSet -> matchSet.getSetResult().getLoserOfSet())
                .collect(Collectors.toList())
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());
        if (result.isPresent() && result.get().getValue() == 2) {
            return result.get().getKey();

        } else return Optional.empty();
    }
}
