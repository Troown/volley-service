package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.DoubleElimination;
import com.volleyservice.SystemDataProvider.MatchesToRoundRelation;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.enums.Phase;
import com.volleyservice.enums.Seed;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

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
                .setRounds(getRoundsFromSchema(doubleElimination.getMatchesToRoundRelations()));

        return tournamentRepository.save(updateTournament);
    }

    public List<Round> findAlInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRounds();
    }

    private List<Round> getRoundsFromSchema(List<MatchesToRoundRelation> matchesToRoundsRelations) {
        return matchesToRoundsRelations.stream().map(rel ->
                new Round(
                        rel.getRoundNumber(),
                        rel.getPhase(),
                        rel.getMatchNumbers().stream().map(Match::new).collect(toList()))
        ).collect(toList());
    }
    public Round findByPhase(long tournamentId, Phase phase) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRounds().stream().filter(round -> round.getPhase() == phase).findAny()
                .orElseThrow(NotFoundException::withRoundNotFound);
    }

    public void setTeamsInFirstRound(Round firstRound, Map<Seed, Team> tournamentSeeding) {
        this.doubleElimination.getSeedToMatchRelations()
                .forEach(
                        rel -> findMatchByNumber(firstRound, rel.getMatchNumber())
                                .setTeams(
                                        rel.getSeeds().stream()
                                                .map(tournamentSeeding::get).collect(toList())));

    }

    private Match findMatchByNumber(Round round, int mNum) {
        return round.getMatches().stream().filter(match -> match.getMatchNumber() == mNum)
                .findAny().orElseThrow(NotFoundException::withMatchNotFound);
    }

}
