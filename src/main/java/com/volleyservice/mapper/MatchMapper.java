package com.volleyservice.mapper;

import com.volleyservice.entity.*;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class MatchMapper {
    public MatchTO mapsToTO(Match match) {

        return new MatchTO(
                match.getMatchNumber(),
                match.getTeams().stream()
                        .collect(toMap(Team::getTeamName, team -> getNumberOfSetsWonByTeam(match, team))));
    }

    private Long getNumberOfSetsWonByTeam(Match match, Team team) {
        return match.getSets().stream().map(MatchSet::getSetResult).map(SetResult::getWinnerOfSet)
                .flatMap(Optional::stream)
                .filter(winner -> winner.equals(team)).count();
    }
}
