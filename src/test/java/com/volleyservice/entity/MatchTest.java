package com.volleyservice.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class MatchTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;

    @Test
    public void shouldReturnWinnerOfTheMatchWhenResultIs2to0() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        Set set1 = new Set(1, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        Set set2 = new Set(2, 21,
                new SetResult(
                new TeamSetPoint(r1, 25),
                new TeamSetPoint(r16, 23)));

        match.setSets(List.of(set1, set2));

//        when
        Optional<Team> result = match.getWinner();

//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r1);
    }

    @Test
    public void shouldReturnWinnerOfTheMatchWhen3sets() {

//        given
        Match match = new Match(1, List.of(r1, r16));

        Set set1 = new Set(1, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        Set set2 = new Set(2, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 23)));

        Set set3 = new Set(3, 15,
                new SetResult(
                new TeamSetPoint(r1, 15),
                new TeamSetPoint(r16, 11)));

        match.setSets(List.of(set1, set2, set3));

//        when
        Optional<Team> result = match.getWinner();

//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r1);
    }
    @DisplayName("There shouldn't be winner as none of teams achieved two won sets")
    @Test
    public void shouldReturnOptionalEmptyAsWinnerWhenMatchIsNotFinished() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        Set set1 = new Set(1, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        Set set2 = new Set(2, 21,
                new SetResult(
                new TeamSetPoint(r1, 19),
                new TeamSetPoint(r16, 21)));

        match.setSets(List.of(set1, set2));

//        when
        Optional<Team> result = match.getWinner();
//        then
        assertThat(result).isEmpty();
    }
    @DisplayName("There shouldn't be winner as none of teams achieved two won sets")
    @Test
    public void shouldReturnOptionalEmptyAsWinnerWhenMatchIsNotFinished2() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        Set set1 = new Set(1, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        match.setSets(List.of(set1));

//        when
        Optional<Team> result = match.getWinner();

//        then
        assertThat(result).isEmpty();
    }
    @Test
    public void shouldReturnOptionalEmptyWhenScoreWasNotSet() {

//        given
        Match match = new Match(1, List.of(r1, r16));

//        when
        Optional<Team> result = match.getWinner();

//        then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnLoser() {
        //        given
        Match match = new Match(1, List.of(r1, r16));

        Set set1 = new Set(1, 21,
                new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        Set set2 = new Set(2, 21,
                new SetResult(
                new TeamSetPoint(r1, 25),
                new TeamSetPoint(r16, 23)));

        match.setSets(List.of(set1, set2));

//        when
        Optional<Team> result = match.getLoser();

//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);
    }
}