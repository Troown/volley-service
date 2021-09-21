package com.volleyservice.mapper;

import com.volleyservice.entity.Player;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.entity.Team;
import com.volleyservice.exception.ValidationException;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.service.PlayerService;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class TeamMapper {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;


    public Team mapsToEntity(TeamRequestTO teamRequestTO) {
        return new Team(teamRequestTO.getIds().stream()
                .map(id -> playerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Player was not found for parameter {id:" + id + "}")))
                .collect(toList()));
    }

    public TeamTO mapsToTO(Team team) {
        return new TeamTO(team.getId(),
                team.getPlayers().stream().map(playerMapper::mapsToTO).collect(toList()),
                team.getRankingPoints());
    }

}
