package com.volleyservice.entity;

import com.volleyservice.enums.Cards;
import lombok.Data;

import java.util.*;

@Data
public class MatchSet {
    private Integer finishPoint;

    private Map<BVBTeam, Integer> teamToPointsMap;

    private Map<Player, List<Cards>> playerToCards;

    public MatchSet(Integer finishPoint, BVBTeam teamA, Integer pointsTeamA, BVBTeam teamB, Integer pointsTeamB) {
        this.finishPoint = finishPoint;
        this.teamToPointsMap = new HashMap<>();
        this.teamToPointsMap.put(teamA, pointsTeamA);
        this.teamToPointsMap.put(teamB, pointsTeamB);
    }

    public Optional<BVBTeam> getWinner() {

        BVBTeam teamWithMorePoints = Collections.max(teamToPointsMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        if (teamToPointsMap.get(teamWithMorePoints) >= finishPoint) {
            return Optional.of(teamWithMorePoints);
        }
        else return Optional.empty();

    }

}
