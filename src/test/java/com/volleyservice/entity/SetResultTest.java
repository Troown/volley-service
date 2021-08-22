package com.volleyservice.entity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SetResultTest {

    private final Team r1 = new Team(List.of(new Player("Mateo", "Piano"),
            new Player("Mateo", "Martino")));
    private final Team r16 = new Team(List.of(new Player("Ivan", "Treger"),
            new Player("Ivan", "Milijkovic")));
    private final Team r11 = new Team(List.of(new Player("Mateo", "Piano"),
            new Player("Mateo", "Martino")));

    @Test
    public void shouldReturnWinnerAndLoserOfSet() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19));

        Optional<Team> winner = setResult.getWinnerOfSet();
        Optional<Team> loser = setResult.getLoserOfSet();

//        then
        assertThat(winner).isEqualTo(Optional.of(r1));
        assertThat(loser).isEqualTo(Optional.of(r16));
    }

    @Test
    public void shouldReturnOptionalEmptySinceSetIsNotSettled() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 21));
//        when
        Optional<Team> winner = setResult.getWinnerOfSet();
        Optional<Team> loser = setResult.getLoserOfSet();

//        then
        assertThat(winner).isEqualTo(Optional.empty());
        assertThat(loser).isEqualTo(Optional.empty());
    }

    @Disabled
    @Test
    public void returnOptionalEmptyAsLoserWhenTwoTeamsHaveEqualsFields() {

//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r11, 19));
//        when
        Optional<Team> loser = setResult.getLoserOfSet();

//        then
        assertThat(loser).isEqualTo(Optional.of(r1));
    }

    @Test
    public void getLoserReturnOptionalEmptyWhenScoreIs0to0() {
//        given
        SetResult setResult = new SetResult(
                new TeamSetPoint(r1, 0),
                new TeamSetPoint(r16, 0));
//        when
        Optional<Team> result = setResult.getLoserOfSet();
//        then
        assertThat(result).isEqualTo(Optional.empty());
    }
}