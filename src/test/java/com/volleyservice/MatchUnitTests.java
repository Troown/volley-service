package com.volleyservice;

import com.volleyservice.entity.Team;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatchUnitTests {
//    Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
//            new Player("Paweł", "Lewandowski")));
//
//    Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
//            new Player("Maciej", "Kałuża")));
//
//    Match match = new Match(List.of(r1, r16));
//    @Test
//    void shouldGetWinnerAndLoser() {
////        Given
//        match.getSets().get(0).setTeamToPointsMap(Map.of(r1, 21, r16, 19));
//        match.getSets().get(1).setTeamToPointsMap(Map.of(r1, 21, r16, 19));
////        When
//        Optional<Team> result1 = match.getWinner();
//        Optional<Team> result2 = match.getLoser();
////        Then
//        Assertions.assertEquals(Optional.of(r1), result1);
//        Assertions.assertEquals(Optional.of(r16), result2);
//}

    @Test
    void shouldGetWinnerAndLoserWhenTieBreak() {
////        Given
//        match.getSets().get(0).setTeamToPointsMap(Map.of(r1, 21, r16, 19));
//        match.getSets().get(1).setTeamToPointsMap(Map.of(r1, 11, r16, 21));
//        match.getSets().get(2).setTeamToPointsMap(Map.of(r1, 15, r16, 17));
////        When
//        Optional<Team> result1 = match.getWinner();
//        Optional<Team> result2 = match.getLoser();
//
////        Then
//        Assertions.assertEquals(Optional.of(r16), result1);
//        Assertions.assertEquals(Optional.of(r1), result2);
    }

    @Test
    void shouldReturnOptionalEmptyWhenGameIsNotFinish() {
////        Given
//        match.getSets().get(0).setTeamToPointsMap(Map.of(r1, 21, r16, 19));
//        match.getSets().get(1).setTeamToPointsMap(Map.of(r1, 11, r16, 21));
////        When
//        Optional<Team> result1 = match.getWinner();
//        Optional<Team> result2 = match.getLoser();
////        Then
//        Assertions.assertEquals(Optional.empty(), result1);
//        Assertions.assertEquals(Optional.empty(), result2);
//    }
    }
}

