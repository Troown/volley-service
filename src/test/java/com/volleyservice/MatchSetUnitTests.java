package com.volleyservice;

import com.volleyservice.entity.Team;
import com.volleyservice.entity.MatchSet;
import com.volleyservice.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MatchSetUnitTests {
    Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
                 new Player("Paweł", "Lewandowski")));

    Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
                  new Player("Maciej", "Kałuża")));

    @Test
    void shouldReturnTrueOnlyIfIsTwoPointsAdvance() {
//        Given
        MatchSet set1 = new MatchSet(21, Map.of(r1, 21, r16, 19));
        MatchSet set2 = new MatchSet(21, Map.of(r1, 21, r16, 22));
        MatchSet set3 = new MatchSet(15, Map.of(r1, 0, r16, 21));
        MatchSet set4 = new MatchSet(15, Map.of(r1, 34, r16, 34));
//        When
        boolean result1 = set1.isTwoPointsDeference();
        boolean result2 = set2.isTwoPointsDeference();
        boolean result3 = set3.isTwoPointsDeference();
        boolean result4 = set4.isTwoPointsDeference();
//        Then
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertTrue(result3);
        Assertions.assertFalse(result4);
    }

    @Test
    void shouldReturnWinnerAndLoserOfGivenSets() {
//        Given
        MatchSet set1 = new MatchSet(21, Map.of(r1, 21, r16, 19));
        MatchSet set2 = new MatchSet(21, Map.of(r1, 21, r16, 22));
        MatchSet set3 = new MatchSet(15, Map.of(r1, 18, r16, 21));
        MatchSet set4 = new MatchSet(15, Map.of(r1, 34, r16, 32));
//        When
        Optional<Team> result1 = set1.getWinnerOfSet();
        Optional<Team> result2 = set2.getWinnerOfSet();
        Optional<Team> result3 = set3.getLoserOfSet();
        Optional<Team> result4 = set4.getLoserOfSet();
//        Then
        Assertions.assertEquals(Optional.of(r1), result1);
        Assertions.assertEquals(Optional.empty(), result2);
        Assertions.assertEquals(Optional.of(r1), result3);
        Assertions.assertEquals(Optional.of(r16), result4);
    }
}
