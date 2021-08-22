package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.DoubleElimination;
import com.volleyservice.SystemDataProvider.MatchesToRoundsRelations;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Tournament;
import com.volleyservice.enums.Phase;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class RoundService {
    private final TournamentRepository tournamentRepository;
    private final DoubleElimination doubleElimination;

    public Tournament createAllRounds(long id, Integer numberOfTeam) {
        Tournament updateTournament = tournamentRepository.findById(id)
                .orElseThrow(NotFoundException::withTournamentNotFound);

        updateTournament
                .setRounds(getRoundsFromSchema(doubleElimination.matchesToRoundsRelationsDefiner()));

        updateTournament.setTeamsInFirstRound();

        return tournamentRepository.save(updateTournament);
    }

    public List<Round> findAlInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRounds();
//TODO shouldReturnAllRoundsFromRepo, shouldThrowException
    }

    private List<Round> getRoundsFromSchema(List<MatchesToRoundsRelations> matchesToRoundsRelations) {
        return matchesToRoundsRelations.stream().map(rel ->
                new Round(
                        rel.getRoundNumber(),
                        rel.getPhase(),
                        rel.getMatchNumbers().stream().map(Match::new).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }
//TODO Test shouldReturnPhase ShouldNotReturnPhase, ShouldThrowException
    public Round findByPhase(long tournamentId, Phase phase) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRounds().stream().filter(round -> round.getPhase() == phase).findAny()
                .orElseThrow(NotFoundException::withRoundNotFound);
    }
}
