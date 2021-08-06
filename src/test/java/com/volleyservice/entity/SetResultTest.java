package com.volleyservice.entity;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class SetResultTest {
    private final Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    private final  Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

//    @Test
//    public void shouldReturnWinnerAndLoserOfSet() {
//        given
//        SetResult setResult = new SetResult(List.of(r1, r16));
//        setResult.getFirstTeamSetResult().setPoints(21);
//        setResult.getSecondTeamSetResult().setPoints(19);
////        when
//        Optional<Team> result = setResult.getWinnerOfSet();
//        Optional<Team> result2 = setResult.getLoserOfSet();
//
////        then
//        assertThat(result).isEqualTo(Optional.of(r1));
//        assertThat(result2).isEqualTo(Optional.of(r16));
//    }

}