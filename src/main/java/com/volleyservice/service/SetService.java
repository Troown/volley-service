package com.volleyservice.service;

import com.volleyservice.entity.Match;
import com.volleyservice.entity.Set;
import com.volleyservice.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;

@Transactional
@Service
@RequiredArgsConstructor
public class SetService {

    private final MatchService matchService;

    public void saveFinalResult(long tournamentId, int matchNumber, List<Set> sets) {
        Match matchToUpdate = matchService.findByMatchNumber(tournamentId, matchNumber);
        validateSets(matchToUpdate, sets);
        matchToUpdate.setSets(sets);
    }

    public List<Set> findAll(long tournamentId, int matchNumber) {
        Match match = matchService.findByMatchNumber(tournamentId, matchNumber);
        return match.getSets();
    }

    public void validateSets(Match match, List<Set> sets) {
        if (!sets.stream().allMatch(this::resultIsValid)) {
            throw new ValidationException("one of set has invalid result");
        }
        if (!numbersOfSetAreInOrder(sets)) {
            throw new ValidationException("sets are not in Order");
        }
        if (!matchAndSetsContainsTheSameTeams(match, sets)) {
            throw new ValidationException("wrong team");
        }
    }

    public boolean resultIsValid(Set set) {
        List<Integer> points = getPointsToList(set);
        if (points.stream().allMatch(p -> p >= 0 && p <= set.getLastPoint())) {
            return true;
        } else return points.stream().anyMatch(p -> p > set.getLastPoint() && isLessOrEqualTwoPointsDifferent(points));
    }

    public boolean matchAndSetsContainsTheSameTeams(Match match, List<Set> sets) {
        return sets.stream()
                .map(Set::getSetResult)
                .map(res -> List.of(
                        res.getFirstTeamSetResult().getTeam(),
                        res.getSecondTeamSetResult().getTeam()))
                .allMatch(list -> list.equals(match.getTeams()));
    }

    public boolean numbersOfSetAreInOrder(List<Set> sets) {
        return sets.stream()
                .map(Set::getSetNumber)
                .collect(toList())
                .equals(IntStream.rangeClosed(1, sets.size())
                        .boxed().collect(toList()));
    }

    private boolean isLessOrEqualTwoPointsDifferent(List<Integer> points) {
        return abs(points.get(0) - points.get(1)) <= 2;
    }
    private List<Integer> getPointsToList(Set set) {
        return List.of(set.getSetResult().getFirstTeamSetResult().getPoints(),
                set.getSetResult().getSecondTeamSetResult().getPoints());
    }
}
