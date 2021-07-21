package com.volleyservice.service;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.Team;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.TeamRequestTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamValidator {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;

    public Team validateTeam(long tournamentId, TeamRequestTO teamRequestTO) {
        List<Player> players = validatePlayers(teamRequestTO);

        return teamRepository.save(new Team(players));

//        if (playerAlreadySign(tournamentId, players)) {
//            throw new IllegalArgumentException("player already signed");
//        } else {
//            return teamToSignIn(players);
//        }
    }

    private boolean playerAlreadySign(Long tournamentId, List<Player> players) {
        var registeredPlayersIds = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("no such tournament"))
                .getRegisteredTeams().stream().map(Team::getPlayers).collect(Collectors.toList())
                .stream().flatMap(List::stream).collect(Collectors.toList())
                .stream().map(Player::getId).collect(Collectors.toList());

        return players.stream().map(Player::getId).collect(Collectors.toList())
                .stream().anyMatch(registeredPlayersIds::contains);
    }

    private List<Player> validatePlayers(TeamRequestTO teamRequestTO) {
        Player player1 = playerRepository.findById(teamRequestTO.getPlayerNumberOneId())
                .orElseThrow(() -> new NoSuchElementException("there is no player with id = "
                        + teamRequestTO.getPlayerNumberOneId()));

        Player player2 = playerRepository.findById(teamRequestTO.getPlayerNumberTwoId())
                .orElseThrow(() -> new NoSuchElementException("there is no such player"));

        return List.of(player1, player2);
    }

    private Team teamToSignIn(List<Player> players) {

        List<Team> teamsInRepository = (List<Team>) teamRepository.findAll();

        return teamsInRepository.stream().filter(team -> team.getPlayers().contains(players))
                .findFirst().orElse(teamRepository.save(new Team(players)));

    }

}
