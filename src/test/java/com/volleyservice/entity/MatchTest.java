package com.volleyservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    private final Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    private final  Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

//    @Test
//    public void shouldReturnWinnerAndLoser() {

////        given
//        Match match = new Match(1, List.of(r1, r16));
//        MatchSet set1 = new MatchSet(1, Map.of(r1, 21, r16, 19));
//        MatchSet set2 = new MatchSet(2, Map.of(r1, 21, r16, 23));
//        MatchSet set3 = new MatchSet(3, Map.of(r1, 15, r16, 10));
//        match.setSets(List.of(set1, set2, set3));
//
////        when
//        var result1 = match.getWinner();
//        var result2 = match.getLoser();
//
////        then
//        assertThat(result1).isEqualTo(Optional.of(r1));
//        assertThat(result2).isEqualTo(Optional.of(r16));
//    }

    @Test
    public void shouldReturnOptionalEmptyIfScoreWasNotSet() {

//        given
        Match match = new Match(1, List.of(r1, r16));

//        when
        Optional<Team> result1 = match.getWinner();
        Optional<Team> result2 = match.getLoser();

//        then
        assertThat(result1).isEqualTo(Optional.empty());
        assertThat(result2).isEqualTo(Optional.empty());
    }

//    @Test
//    public void shouldReturnOptionalEmptyIfMatchIsNotSettled() {
//
////        given
//        Match match = new Match(1, List.of(r1, r16));
//        MatchSet set1 = new MatchSet(1, Map.of(r1, 21, r16, 19));
//        MatchSet set2 = new MatchSet(2, Map.of(r1, 10, r16, 21));
//        match.setSets(List.of(set1, set2));
//
////        when
//        var result1 = match.getWinner();
//        var result2 = match.getLoser();
//
////        then
//        assertThat(result1).isEqualTo(Optional.empty());
//        assertThat(result2).isEqualTo(Optional.empty());
//    }
}