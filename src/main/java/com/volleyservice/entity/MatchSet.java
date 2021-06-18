package com.volleyservice.entity;

import com.volleyservice.enums.Cards;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.validation.constraints.NotNull;
import java.util.*;

@NoArgsConstructor
@Data
public class MatchSet {
    private Integer lastPoint;
    private Map<BVBTeam, Integer> teamToPointsMap;
    private Map<Player, List<Cards>> playerToCards;

    public MatchSet(Integer lastPoint, Map<BVBTeam, Integer> teamToPointsMap) {
        this.lastPoint = lastPoint;
        this.teamToPointsMap = teamToPointsMap;
    }

    public Optional<BVBTeam> getWinnerOfSet() {
        BVBTeam teamWithMorePoints = Collections.max(teamToPointsMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        if (teamToPointsMap.get(teamWithMorePoints) >= lastPoint && isTwoPointsDeference()) {
            return Optional.of(teamWithMorePoints);
        }
        else return Optional.empty();
    }
    public Optional<BVBTeam> getLoserOfSet() {
        BVBTeam teamWithMorePoints = Collections.max(teamToPointsMap.entrySet(), Map.Entry.comparingByValue()).getKey();
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
