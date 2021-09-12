package com.volleyservice.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
public class ReturnWinnerAndLoserOfSetTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;

    @Test
    void shouldReturnTeamThatWonASet() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19)));
//        when
        var result = set.getSetWinner();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r1);
    }
    @Test
    void shouldReturnTeamThatWonASetWhenIsOverLastPoint() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 26),
                        new TeamSetPoint(r16, 28)));
//        when
        var result = set.getSetWinner();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);
    }

    @Test
    void shouldReturnOptionalEmptyAsWinnerWhenLastPointNotAchieved() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 20),
                        new TeamSetPoint(r16, 17)));
//        when
        var result = set.getSetWinner();
//        then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnOptionalEmptyAsWinnerWhenLastNotTwoPointsDifferent() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 24),
                        new TeamSetPoint(r16, 23)));
//        when
        var result = set.getSetWinner();
//        then
        assertThat(result).isEmpty();
    }
    @Test
    void shouldReturnTeamThatLostASet() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19)));
//        when
        var result = set.getSetLoser();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);
    }
    @Test
    void shouldReturnTeamThatLostASetWhenIsOverLastPoint() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 26),
                        new TeamSetPoint(r16, 28)));
//        when
        var result = set.getSetLoser();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r1);
    }

    @Test
    void shouldReturnOptionalEmptyAsLoserWhenLastPointNotAchieved() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 11),
                        new TeamSetPoint(r16, 20)));
//        when
        var result = set.getSetLoser();
//        then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnOptionalEmptyAsLoserWhenNotTwoPointsDifferent() {
//        given
        Set set = new Set(
                1,
                21,
                new SetResult(
                        new TeamSetPoint(r1, 24),
                        new TeamSetPoint(r16, 23)));
//        when
        var result = set.getSetLoser();
//        then
        assertThat(result).isEmpty();
    }

}
