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

import static java.util.stream.Collectors.toList;

@Transactional
@Service
@RequiredArgsConstructor
public class MatchService {
    private final TournamentRepository tournamentRepository;

    public List<Match> findAlInTournament(long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .getRounds().stream().map(Round::getMatches).flatMap(List::stream).collect(toList());
    }

    public Match saveResult(long tourId, Integer matchNum, List<MatchSet> sets) {
        Match matchToUpdate = findByMatchNumber(tourId, matchNum);

        matchToUpdate.setSets(sets);

        return matchToUpdate;
    }

    public Match findByMatchNumber(long tournamentId, Integer matchNumber) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .findMatchByNumber(matchNumber)
                .orElseThrow(NotFoundException::withMatchNotFound);
    }

}
