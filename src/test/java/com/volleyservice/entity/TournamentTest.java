package com.volleyservice.entity;

import com.volleyservice.enums.Phase;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class TournamentTest {

    @Test
    void shouldFindMatchByNumber() {
//        given
        Tournament tournament = new Tournament("Cup");

        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3)))));

//        when
        Optional<Match> result = tournament.findMatchByNumber(3);
//        then
        assertThat(result.get().getMatchNumber()).isEqualTo(3);
    }

    @Test
    void shouldReturnOptionalEmptyWhenMatchDoesNotExist() {
//        given
        Tournament tournament = new Tournament("Cup");

        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3)))
                )
        );

//        when
        Optional<Match> result = tournament.findMatchByNumber(4);
//        then
        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    void shouldFindMatchByNumberInTournamentWithManyRounds() {
//        given
        Tournament tournament = new Tournament("Cup");

        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3))),
                        new Round(2, Phase.SECOND_ROUND, List.of(
                                new Match(4),
                                new Match(5),
                                new Match(6)))
                )
        );

//        when
        Optional<Match> result = tournament.findMatchByNumber(6);
//        then
        assertThat(result.get().getMatchNumber()).isEqualTo(6);
    }

    @Test
    void shouldFindMatchByNumberInTournamentWithManyRounds2() {
//        given
        Tournament tournament = new Tournament("Cup");
        Match match = new Match(6);

        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3))),
                        new Round(2, Phase.SECOND_ROUND, List.of(
                                new Match(4),
                                new Match(5),
                                match))
                )
        );

//        when
        Optional<Match> result = tournament.findMatchByNumber(6);
//        then
        assertThat(result.get()).isEqualTo(match);
    }

    @Test
    void shouldReturnOptionalEmptyWhenThereIsNoMatchWithGivenNumber() {
//        given
        Tournament tournament = new Tournament("Cup");

        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3))),
                        new Round(2, Phase.SECOND_ROUND, List.of(
                                new Match(4),
                                new Match(5),
                                new Match(6)))
                )
        );

//        when
        Optional<Match> result = tournament.findMatchByNumber(9);
//        then
        assertThat(result).isEqualTo(Optional.empty());
    }
}