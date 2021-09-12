package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.MatchesRelation;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Set;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Tournament;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public Match saveResult(long tourId, Integer matchNum, List<Set> sets) {
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

    public boolean matchAndSetsContainsTheSameTeams(Match match, List<Set> sets) {
        return sets.stream()
                .map(Set::getSetResult)
                .map(res -> List.of(
                        res.getFirstTeamSetResult().getTeam(),
                        res.getSecondTeamSetResult().getTeam()))
                .allMatch(list -> list.containsAll(match.getTeams()));
    }

    public boolean setsNumbersAreInOrder(List<Set> sets) {
        return true;

    }

    public List<Match> updateRelatedMatches(Match sourceMatch, Tournament tournament, List<MatchesRelation> relations) {
        return new ArrayList<>();
    }
}
