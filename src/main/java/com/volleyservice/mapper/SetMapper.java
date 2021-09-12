package com.volleyservice.mapper;

import com.volleyservice.entity.Set;
import com.volleyservice.entity.SetResult;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.TeamSetPoint;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.to.SetRequestTO;
import com.volleyservice.to.SetTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class SetMapper {
    private final TeamRepository teamRepository;

    public Set mapsToEntity(SetRequestTO setRequestTO) {
        Team team1 = findTeamByIdOrThrowNotFound(setRequestTO.getFirstTeamId());
        Team team2 = findTeamByIdOrThrowNotFound(setRequestTO.getSecondTeamId());

        return new Set(
                setRequestTO.getSetNumber(),
                setRequestTO.getLastPoint,
                new SetResult(
                        new TeamSetPoint(team1, setRequestTO.getFirstTeamPoints()),
                        new TeamSetPoint(team2, setRequestTO.getSecondTeamPoints())
                ));
    }

    private Team findTeamByIdOrThrowNotFound(long id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Team does not exist for id = " + id));
    }

    public SetTO mapsToTO(Set set) {
        return new SetTO(
                set.getSetNumber(),
                Map.of(
                        set.getSetResult().getFirstTeamSetResult().getTeam(),
                        set.getSetResult().getFirstTeamSetResult().getPoints(),
                        set.getSetResult().getSecondTeamSetResult().getTeam(),
                        set.getSetResult().getSecondTeamSetResult().getPoints()));
    }

}
