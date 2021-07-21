package com.volleyservice.mapper;

import com.volleyservice.entity.Player;
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

@RequiredArgsConstructor
@Service
public class TeamMapper {
    private final TeamService teamService;
    private final PlayerService playerService;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TournamentRepository tournamentRepository;
    private final PlayerMapper playerMapper;


    public Team mapsToEntity(TeamRequestTO teamRequestTO) {

        Player player1 = playerRepository.findById(teamRequestTO.getPlayerNumberOneId())
                .orElseThrow(() -> new NoSuchElementException("there is no such player"));
        Player player2 = playerRepository.findById(teamRequestTO.getPlayerNumberTwoId())
                .orElseThrow(() -> new NoSuchElementException("there is no such player"));

        List<Player> players = List.of(player1, player2);

        return new Team(List.of(player1, player2));
    }

    public TeamTO mapsToTO(@NotNull Team team) {

        return new TeamTO(team.getId(),
                team.getPlayers().stream().map(playerMapper::mapsToTO).collect(Collectors.toList()),
                team.getRankingPoints());
    }

}
