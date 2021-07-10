package com.volleyservice.mapper;

import com.volleyservice.entity.Player;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.entity.Team;
import com.volleyservice.exception.ValidationException;
import com.volleyservice.service.PlayerService;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamMapper {
    private final TeamService teamService;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;


    public Team mapsToEntity(TeamRequestTO teamRequestTO) {

        Player player1 = playerRepository.findById(teamRequestTO.getPlayerNumberOneId())
                .orElseThrow(()->new NoSuchElementException("there is no such player"));
        Player player2 = playerRepository.findById(teamRequestTO.getPlayerNumberTwoId())
                .orElseThrow(()->new NoSuchElementException("there is no such player"));
        return new Team(List.of(player1, player2));
    }

    public TeamTO mapsToTO(Team team) {
        String player1NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(0);
        String player2NameSurname = team.getPlayers().stream()
                .map(player -> player.getName()+" "+player.getSurname()).collect(Collectors.toList()).get(1);

        return new TeamTO(player1NameSurname, player2NameSurname, team.getRankingPoints());
    }

}
