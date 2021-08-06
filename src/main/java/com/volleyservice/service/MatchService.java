package com.volleyservice.service;

import com.volleyservice.entity.Match;
import com.volleyservice.entity.MatchSet;
import com.volleyservice.entity.Round;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.MatchRepository;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.to.MatchTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class MatchService {
    private final TournamentRepository tournamentRepository;

    public List<Match> findAlInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Tournament does not exist"))
                .getRounds().stream().map(Round::getMatches).collect(Collectors.toList())
                .stream().collect(ArrayList::new, List::addAll, List::addAll);
    }

    public Optional<Match> setResult(long tourId, Integer matchNum, List<MatchSet> sets) {
        Match matchToUpdate = tournamentRepository.findById(tourId)
                .orElseThrow(() -> new NotFoundException("Tournament does not exist"))
                .findMatchByNumber(matchNum)
                .orElseThrow(()->
                        new NotFoundException("Match " + matchNum + " does not exist in tournament with id=" + tourId));

        validateTeamsInSets(matchToUpdate, sets);

        matchToUpdate.setSets(sets);

        return Optional.of(matchToUpdate);
    }

    private boolean validateTeamsInSets(Match match, List<MatchSet> sets) {

        return sets.stream().allMatch(set -> set.getTeams().containsAll(match.getTeams()));
    }

    public Optional<Match> findByMatchNumber(long tournamentId, Integer matchNumber) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NotFoundException("Tournament does not exist for id = " + tournamentId))
                .findMatchByNumber(matchNumber);
    }
}
