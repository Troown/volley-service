package com.volleyservice.mapper;

import com.volleyservice.entity.Match;
import com.volleyservice.entity.Team;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.MatchRequestTO;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchMapper {

    private final TeamService teamService;

    public Match mapsToEntity(MatchRequestTO matchRequestTO) {
        Team teamOne = teamService.findById(matchRequestTO.getTeamOneID()).get();
        Team teamTwo = teamService.findById(matchRequestTO.getTeamTwoId()).get();

        return new Match(matchRequestTO.getMatchNumber(), List.of(teamOne, teamTwo));
    }

    public MatchTO mapsToTO(Match match) {
        List<List<Integer>> result = match.getSets().stream().map(set -> List.of(set.getSetResult().getFirstTeamSetResult().getPoints()
                , set.getSetResult().getSecondTeamSetResult().getPoints())).collect(Collectors.toList());
        return new MatchTO(match.getTeams().get(0).getTeamName()
                , match.getTeams().get(1).getTeamName()
                , result);
    }

}
