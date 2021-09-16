package com.volleyservice.service;

import com.volleyservice.entity.*;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ValidateSetTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;
    @Mock
    private Team r2;
    @Mock
    private SetResult setResult;

    @Mock
    private TournamentRepository repo;
    @InjectMocks
    private SetService setService;

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
        boolean result = setService.matchAndSetsContainsTheSameTeams(match, sets);
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
        boolean result = setService.matchAndSetsContainsTheSameTeams(match, sets);
//        then
        assertThat(result).isEqualTo(true);
    }

    @Test
    void shouldReturnFalseWhenTeamsPropertyIsEmpty() {
        //given
        Match match = new Match(12);
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
        boolean result = setService.matchAndSetsContainsTheSameTeams(match, sets);
//        then
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void shouldReturnFalseWhenSetNumbersAre1And3() {

//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21, setResult),
                new Set(3, 15, setResult));

//        when
        boolean result = setService.numbersOfSetAreInOrder(sets);
//        then
        assertThat(result).isEqualTo(false);
    }
    @Test
    public void shouldReturnTrueWhenSetNumbersAreInOrder() {
//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(1, 21, setResult),
                new Set(2, 15, setResult),
                new Set(3, 15, setResult));

//        when
        boolean result = setService.numbersOfSetAreInOrder(sets);
//        then
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void shouldReturnFalseWhenSetNumbersAre234() {
//        given
        Match match = new Match(12, List.of(r1, r16));
        List<Set> sets = List.of(
                new Set(2, 21, setResult),
                new Set(3, 15, setResult),
                new Set(4, 15, setResult));
//        when
        boolean result = setService.numbersOfSetAreInOrder(sets);
//        then
        assertThat(result).isEqualTo(false);
    }
}
