package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.MatchesRelation;
import com.volleyservice.entity.*;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.counting;
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

    public Match findByMatchNumber(long tournamentId, Integer matchNumber) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(NotFoundException::withTournamentNotFound)
                .findMatchByNumber(matchNumber)
                .orElseThrow(NotFoundException::withMatchNotFound);
    }

    public List<Match> updateRelatedMatches(Match sourceMatch, Tournament tournament, List<MatchesRelation> relations) {
        return new ArrayList<>();
    }
}
