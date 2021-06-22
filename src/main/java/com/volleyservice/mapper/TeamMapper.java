package com.volleyservice.mapper;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import com.volleyservice.entity.Team;
import com.volleyservice.service.PlayerService;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamMapper {
    TeamService teamService;


    public Team mapsToEntity(PlayerRepository repository, TeamRequestTO teamRequestTO) {
        PlayerService playerService = new PlayerService(repository);

        Optional<Player> player1 = playerService.findById(teamRequestTO.getPlayerNumberOneId());
        Optional<Player> player2 = playerService.findById(teamRequestTO.getPlayerNumberTwoId());

        return new Team(List.of(player1.get(), player2.get()));
    }

    public TeamTO mapsToTO(Team team) {
        String player1NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(0);
        String player2NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(1);

        return new TeamTO(player1NameSurname, player2NameSurname, team.getRankingPoints());
    }

}
