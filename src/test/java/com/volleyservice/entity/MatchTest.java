package com.volleyservice.entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MatchTest {

    private final Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    private final  Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

    @Test
    public void shouldReturnWinnerOfTheMatchWhen2sets() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        MatchSet set1 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        MatchSet set2 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 25),
                new TeamSetPoint(r16, 23)));

        match.setSets(List.of(set1, set2));

//        when
        var winner = match.getWinner();
        var loser = match.getLoser();

//        then
        assertThat(winner).isEqualTo(Optional.of(r1));
        assertThat(loser).isEqualTo(Optional.of(r16));
    }

    @Test
    public void shouldReturnWinnerOfTheMatchWhen3sets() {

//        given
        Match match = new Match(1, List.of(r1, r16));

        MatchSet set1 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        MatchSet set2 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 23)));

        MatchSet set3 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 15),
                new TeamSetPoint(r16, 11)));

        match.setSets(List.of(set1, set2, set3));

//        when
        var winner = match.getWinner();
        var loser = match.getLoser();

//        then
        assertThat(winner).isEqualTo(Optional.of(r1));
        assertThat(loser).isEqualTo(Optional.of(r16));
    }

    @Test
    public void getWinnerShouldReturnOptionalEmptyWhenMatchIsNotFinished() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        MatchSet set1 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        MatchSet set2 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 19),
                new TeamSetPoint(r16, 21)));

        match.setSets(List.of(set1, set2));

//        when
        var winner = match.getWinner();
        var loser = match.getLoser();

//        then
        assertThat(winner).isEqualTo(Optional.empty());
        assertThat(loser).isEqualTo(Optional.empty());
    }

    @Test
    public void getWinnerShouldReturnOptionalEmptyWhenMatchIsNotFinished2() {
//        given
        Match match = new Match(1, List.of(r1, r16));

        MatchSet set1 = new MatchSet(1, new SetResult(
                new TeamSetPoint(r1, 21),
                new TeamSetPoint(r16, 19)));

        match.setSets(List.of(set1));

//        when
        var winner = match.getWinner();
        var loser = match.getLoser();

//        then
        assertThat(winner).isEqualTo(Optional.empty());
        assertThat(loser).isEqualTo(Optional.empty());
    }

    @Test
    public void shouldReturnOptionalEmptyWhenScoreWasNotSet() {

//        given
        Match match = new Match(1, List.of(r1, r16));

//        when
        Optional<Team> result1 = match.getWinner();
        Optional<Team> result2 = match.getLoser();

//        then
        assertThat(result1).isEqualTo(Optional.empty());
        assertThat(result2).isEqualTo(Optional.empty());
    }
}