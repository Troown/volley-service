package com.volleyservice.service;

import com.volleyservice.entity.*;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SetValidatorTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;
    @Mock
    private Team r2;

    @Mock
    private TournamentRepository repo;
    @InjectMocks
    private MatchService matchService;

    @Test
    public void shouldReturnFalseWhenTryToAddSetWithTeamThatIsNotInTheMatch() {
//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(r2, 21),
                        new TeamSetPoint(r16, 19))));
//        when
        boolean result = matchService.matchAndSetsContainsTheSameTeams(match, sets);
//        then
        assertThat(result).isEqualTo(false);
    }
    @Test
    public void shouldReturnTrueWhenSetsContainsExactlyTheSameTeamsLikeMatch() {
//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))));
//        when
        boolean result = matchService.matchAndSetsContainsTheSameTeams(match, sets);
//        then
        assertThat(result).isEqualTo(true);
    }

    @Disabled
    @Test
    public void shouldReturnFalseWhenSetNumbersAreNotInOrder() {

//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(3, 15,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))));

//        when
        boolean result = matchService.setsNumbersAreInOrder(sets);
//        then
        assertThat(result).isEqualTo(false);
    }
    @Test
    public void shouldReturnTrueWhenSetNumbersAreInOrder() {
//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(3, 15,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))));

//        when
        boolean result = matchService.setsNumbersAreInOrder(sets);
//        then
        assertThat(result).isEqualTo(true);
    }


    @Disabled
    @Test
    public void shouldReturnTrueWhen() {

        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new Set(2, 21,
                        new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 23))),
                new Set(3, 15,
                        new SetResult(
                        new TeamSetPoint(r1, 15),
                        new TeamSetPoint(r16, 13))));
        match.setSets(sets);
//        when
        boolean result = matchService.matchAndSetsContainsTheSameTeams(match, sets);
//        then
        assertThat(result).isEqualTo(true);
    }
}
