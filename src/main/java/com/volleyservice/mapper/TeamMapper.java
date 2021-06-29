package com.volleyservice.mapper;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import com.volleyservice.entity.Team;
import com.volleyservice.exception.ValidationException;
import com.volleyservice.service.PlayerService;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamMapper {
    private final TeamService teamService;
    private final PlayerService playerService;


    public Team mapsToEntity(PlayerRepository repository, TeamRequestTO teamRequestTO) {

        Player player1 = getPlayer(teamRequestTO.getPlayerNumberOneId());
        Player player2 = getPlayer(teamRequestTO.getPlayerNumberTwoId());
        return new Team(List.of(player1, player2));
    }

    private Player getPlayer(Long playerId) {
        return playerService.findById(playerId)
                .orElseThrow(() -> new ValidationException("player does not exist"));
    }

    public TeamTO mapsToTO(Team team) {
        String player1NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(0);
        String player2NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(1);

        return new TeamTO(player1NameSurname, player2NameSurname, team.getRankingPoints());
    }

}
