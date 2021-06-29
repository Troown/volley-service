package com.volleyservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    private int matchNumber;
    private List<Team> teams;
    private List<MatchSet> sets;
    private String referee;


    public Match(List<Team> teams, List<MatchSet> sets) {
        this.teams = teams;
        this.sets = sets;
    }

    public Optional<Team> getWinner() {
        Map.Entry<Optional<Team>, Long> result = sets.stream()
                .map(MatchSet::getWinnerOfSet).collect(Collectors.toList())
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
        Map.Entry<Optional<Team>, Long> result = sets.stream()
                .map(MatchSet::getLoserOfSet).collect(Collectors.toList())
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
