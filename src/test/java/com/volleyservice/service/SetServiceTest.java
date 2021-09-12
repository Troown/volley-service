package com.volleyservice.service;

import com.volleyservice.entity.*;
import com.volleyservice.repository.TournamentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SetServiceTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;
    @Mock
    private MatchService matchService;
    @InjectMocks
    SetService setService;

    @Test
    void shouldSetSetsInGivenMatch() {
//        given
        long tournamentId = 2;
        int matchNumber = 10;
        Match match = new Match();
        match.setTeams(List.of(r1, r16));

        List<Set> sets = List.of(
                new Set(1, 21,
                        new SetResult(
                                new TeamSetPoint(r1, 21),
                                new TeamSetPoint(r16, 19)
                        )),
                new Set(2, 21,
                        new SetResult(
                                new TeamSetPoint(r1, 21),
                                new TeamSetPoint(r16, 15)
                        )));
        when(matchService.findByMatchNumber(tournamentId, matchNumber)).thenReturn(match);

//        when
        setService.saveFinalResult(tournamentId, matchNumber, sets);

//        then
        assertThat(match.getSets()).isEqualTo(sets);
        assertThat(match.getSets().size()).isEqualTo(2);
        assertThat(match.getSets().get(0).getSetNumber()).isEqualTo(1);
        assertThat(match.getSets().get(1).getSetResult().getFirstTeamSetResult().getTeam()).isEqualTo(r1);
    }

    @Test
    void shouldReturnListOfSetsFromMatch() {
//        given
        Match match = new Match();
        match.setSets(List.of(
                new Set(1, 21,
                        new SetResult(
                                new TeamSetPoint(r1, 21),
                                new TeamSetPoint(r16, 19))),
                new Set(2, 21,
                        new SetResult(
                                new TeamSetPoint(r1, 21),
                                new TeamSetPoint(r16, 15)))));
        when(matchService.findByMatchNumber(21, 7)).thenReturn(match);
//        when
        List<Set> result = setService.findAll(21, 7);
//        then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getSetNumber()).isEqualTo(1);
        assertThat(result.get(1).getSetResult().getSecondTeamSetResult().getTeam()).isEqualTo(r16);
    }

}
