package com.volleyservice.mapper;

import com.volleyservice.entity.MatchSet;
import com.volleyservice.entity.SetResult;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.TeamSetPoint;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.to.MatchSetRequestTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class MatchSetMapper {
    private final TeamRepository teamRepository;

    public MatchSet mapsToEntity(MatchSetRequestTO matchSetRequestTO) {
        Team team1 = findTeamByIdOrThrowNotFound(matchSetRequestTO.getFirstTeamId());
        Team team2 = findTeamByIdOrThrowNotFound(matchSetRequestTO.getSecondTeamId());

        return new MatchSet(
                matchSetRequestTO.getSetNumber(),
                new SetResult(
                        new TeamSetPoint(team1, matchSetRequestTO.getFirstTeamPoints()),
                        new TeamSetPoint(team2, matchSetRequestTO.getSecondTeamPoints())
                )
        );


    }

    private Team findTeamByIdOrThrowNotFound(long id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Team does not exist for id = " + id));
    }
}
