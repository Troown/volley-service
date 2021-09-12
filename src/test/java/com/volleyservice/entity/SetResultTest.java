package com.volleyservice.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(MockitoExtension.class)
class SetResultTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;

    @Test
    public void shouldReturnWinnerOfFinishedSet() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19),
                21);
//        when
        Optional<Team> result = setResult.getSetWinner();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r1);
    }

    @Test
    public void shouldReturnOptionalEmptyWhenSetIsNotTwoPointsDifference() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 23),
                new TeamSetPoint(r16, 22),
                21);
//        when
        Optional<Team> result = setResult.getSetWinner();
//        then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnOptionalEmptyWhenLastPointWasNotAchieved() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 15),
                new TeamSetPoint(r16, 19),
                21);
//        when
        Optional<Team> result = setResult.getSetWinner();
//        then
        assertThat(result).isEmpty();
    }
    @Test
    public void shouldReturnWinnerOfSetPlayedOverLastPoint() {
        //        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 23),
                new TeamSetPoint(r16, 25),
                21);
//        when
        Optional<Team> result = setResult.getSetWinner();
//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);

    }

    @Test
    public void shouldReturnOptionalEmptyAsLoserWhenLastPointWasNotAchieved() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 19),
                new TeamSetPoint(r16, 12),
                21);
//        when
        Optional<Team> result = setResult.getSetLoser();
//        then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnLoserOfSet() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19),
                21);

        Optional<Team> result = setResult.getSetLoser();

//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);
    }

    @Test
    public void shouldReturnOptionalEmptyAsLoserWhenTeamDoesNotHave2PointsAdvanced() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 23),
                new TeamSetPoint(r16, 24));
//        when
        Optional<Team> result = setResult.getSetLoser();

//        then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnLoserWhenScoreIsOverLastPoint() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 26),
                new TeamSetPoint(r16, 24));
//        when
        Optional<Team> result = setResult.getSetLoser();

//        then
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(r16);
    }
}