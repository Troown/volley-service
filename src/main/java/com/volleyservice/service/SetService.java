package com.volleyservice.service;

import com.volleyservice.entity.Match;
import com.volleyservice.entity.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class SetService {

    private final MatchService matchService;

    public void saveFinalResult(long tournamentId, int matchNumber, List<Set> sets) {
        Match matchToUpdate = matchService.findByMatchNumber(tournamentId, matchNumber);
        matchToUpdate.setSets(sets);
    }

    public List<Set> findAll(long tournamentId, int matchNumber) {
        Match match = matchService.findByMatchNumber(tournamentId, matchNumber);
        return match.getSets();
    }
}
