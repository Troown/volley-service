package com.volleyservice.service;


import com.volleyservice.entity.Match;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.MatchMapper;
import com.volleyservice.repository.TeamRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.TournamentTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
@Data
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;
    private final MatchMapper matchMapper;

    public Tournament save(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> findById(long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> findAll() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public Team saveTeamIntoTournament(Long tournamentId, Team team) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoSuchElementException("no such tournament"));
        List<Team> teams = tournament.getRegisteredTeams();
        teams.add(team);
        tournament.setRegisteredTeams(teams);
        tournamentRepository.save(tournament);
        return tournamentRepository.findById(1L).get().getRegisteredTeams().stream()
                .filter(team1 -> team1.getTeamName() == team.getTeamName()).findAny().get();
    }

    public List<Match> getRound(long tournamentId, long RoundId ) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(NoSuchElementException::new);
        return new ArrayList<>(tournament.generateRound1().getMatches());
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
}
