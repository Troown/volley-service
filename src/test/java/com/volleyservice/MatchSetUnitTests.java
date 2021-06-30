package com.volleyservice;

import com.volleyservice.entity.MatchSet;
import com.volleyservice.entity.Player;
import com.volleyservice.entity.Team;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchSetUnitTests {
    Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

    @Test
    void shouldReturnWinnerAndLoserOfSet() {
        MatchSet set1 = new MatchSet(1, List.of(r1, r16));

        set1.getSetResult().getFirstTeamSetResult().setPoints(21);
        set1.getSetResult().getSecondTeamSetResult().setPoints(18);

        assertThat(set1.getSetResult().getWinnerOfSet()).isEqualTo(Optional.of(r1));
        assertThat(set1.getSetResult().getLoserOfSet()).isEqualTo(Optional.of(r16));
    }
}
