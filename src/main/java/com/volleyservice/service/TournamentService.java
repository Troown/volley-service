package com.volleyservice.service;


import com.volleyservice.entity.Match;
import com.volleyservice.entity.Player;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.exception.ValidationException;
import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TournamentTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament findById(long id) {
        return tournamentRepository.findById(id).orElseThrow(NotFoundException::withTournamentNotFound);
    }

    public List<Tournament> findAll() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public List<Team> findAllTeamsInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRegisteredTeams();
    }

    public Team findOneTeamInTournamentById(long tournamentId, long teamId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRegisteredTeams().stream().filter(team -> team.getId() == teamId)
                .findAny().orElseThrow(NotFoundException::withMatchNotFound);
    }

    public Tournament saveTeamIntoTournament(long tournamentId, Team teamToRegister) {

        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Tournament does not exist"));

        //TODO here should be team validated

        tournament.getRegisteredTeams().add(teamToRegister);

        return tournamentRepository.save(tournament);
    }




}
