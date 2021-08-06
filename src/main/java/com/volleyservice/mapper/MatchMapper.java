package com.volleyservice.mapper;

import com.volleyservice.entity.*;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.MatchRequestTO;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MatchMapper {

    private final TeamService teamService;


    public MatchTO mapsToTO(Match match) {


        Map<String, Long> teamNameToSetsMap = match.getTeams().stream()
                .collect(Collectors.toMap(Team::getTeamName, team -> 0L));

        var winnerOfEachSetList = match.getSets().stream()
                .map(set -> set.getSetResult().getWinnerOfSet()).collect(Collectors.toList());


        teamNameToSetsMap.keySet()
                .forEach(teamName -> teamNameToSetsMap.put(
                        teamName,
                        winnerOfEachSetList.stream()
                        .filter(team -> Objects.requireNonNull(team.orElse(null))
                                .getTeamName().equals(teamName)).count()));


        return new MatchTO(match.getMatchNumber(), teamNameToSetsMap);


    }


}
