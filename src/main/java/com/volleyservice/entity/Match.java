package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Match {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int matchNumber;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Team> teams;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<MatchSet> sets;

    public Match(List<Team> teams) {
        this.teams = teams;
        MatchSet set1 = new MatchSet(1, teams);
        MatchSet set2 = new MatchSet(2, teams);
        MatchSet set3 = new MatchSet(3, teams);
        this.sets = List.of(set1, set2, set3);
    }

    public Match(int matchNumber, List<Team> teams) {
        this.matchNumber = matchNumber;
        this.teams = teams;
        MatchSet set1 = new MatchSet(1, teams);
        MatchSet set2 = new MatchSet(2, teams);
        MatchSet set3 = new MatchSet(3, teams);
        this.sets = List.of(set1, set2, set3);
    }

    public Optional<Team> getWinner() {
        Map.Entry<Optional<Team>, Long> result = sets.stream().map(MatchSet::getSetResult).collect(Collectors.toList())
                .stream().map(SetResult::getWinnerOfSet).collect(Collectors.toList())
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow(IllegalArgumentException::new);

        if (result.getValue() == 2) {
            return result.getKey();
        }
        else return Optional.empty();
    }
    public Optional<Team> getLoser() {
        Map.Entry<Optional<Team>, Long> result = sets.stream().map(MatchSet::getSetResult).collect(Collectors.toList())
                .stream().map(SetResult::getLoserOfSet).collect(Collectors.toList())
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).orElseThrow(IllegalArgumentException::new);

        if (result.getValue() == 2) {
            return result.getKey();
        }
        else return Optional.empty();
    }
}
