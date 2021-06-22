package com.volleyservice.entity;

import com.volleyservice.enums.Cards;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class MatchSet {

    private Integer lastPoint;
    private Map<Team, Integer> teamToPointsMap;
    private Map<Player, List<Cards>> playerToCards;

    public MatchSet(Integer lastPoint, Map<Team, Integer> teamToPointsMap) {
        this.lastPoint = lastPoint;
        this.teamToPointsMap = teamToPointsMap;
    }

    public Optional<Team> getWinnerOfSet() {
        Team teamWithMorePoints = Collections.max(teamToPointsMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        if (teamToPointsMap.get(teamWithMorePoints) >= lastPoint && isTwoPointsDeference()) {
            return Optional.of(teamWithMorePoints);
        }
        else return Optional.empty();
    }
    public Optional<Team> getLoserOfSet() {
        Team teamWithMorePoints = Collections.max(teamToPointsMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        if (teamToPointsMap.get(teamWithMorePoints) >= lastPoint && isTwoPointsDeference()) {
            return Optional.of(Collections.min(teamToPointsMap.entrySet(), Map.Entry.comparingByValue()).getKey());
        }
        else return Optional.empty();
    }

    public boolean isTwoPointsDeference() {//I think it could be private but cant test it then
        return this.teamToPointsMap.values().stream().
                reduce((subtotal, element) -> subtotal - element).map(Math::abs).orElse(0) >= 2;
    }
}
