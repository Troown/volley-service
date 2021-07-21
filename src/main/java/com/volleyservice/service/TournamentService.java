package com.volleyservice.service;


import com.volleyservice.entity.Match;
import com.volleyservice.entity.Player;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TournamentTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> findById(long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> findAll() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public List<Team> findAllTeamsInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("no such tournament"))
                .getRegisteredTeams();
    }

    public Optional<Team> findOneTeamInTournamentById(long tournamentId, long teamId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("no such tournament"))
                .getRegisteredTeams().stream().filter(team -> team.getId() == teamId)
                .findAny();
    }

    public Tournament saveTeamIntoTournament(long tournamentId, Team teamToRegister) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("no such tournament"));

        List<Team> teams = tournament.getRegisteredTeams();
        teams.add(teamToRegister);
        tournament.setRegisteredTeams(teams);

        return tournamentRepository.save(tournament);
    }

}
