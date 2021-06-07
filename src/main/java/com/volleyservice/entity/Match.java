package com.volleyservice.entity;

import lombok.Data;
import org.springframework.http.server.DelegatingServerHttpResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class Match {
    private int matchNumber;
    private List<BVBTeam> teams;
    private List<MatchSet> sets;
    private String referee;
    private BVBTeam winnerTeam;

    public Match(List<BVBTeam> teams, List<MatchSet> sets) {
        this.teams = teams;
        this.sets = sets;
    }

    public Optional<BVBTeam> getWinner() {
        Map.Entry<Optional<BVBTeam>, Long> result = sets.stream()
                .map(MatchSet::getWinner).collect(Collectors.toList())
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
