package com.volleyservice;

import com.volleyservice.entity.MatchSet;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;

public class MatchUnitTests {
    Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

    Match match = new Match(List.of(r1, r16));

    @Test
    void shouldGetWinnerAndLoser() {
        match.getSets().stream().filter(set -> set.getSetNumber() == 1).findFirst().get()
                .getSetResult().getFirstTeamSetResult().setPoints(21);
        match.getSets().stream().filter(set -> set.getSetNumber() == 1).findFirst().get()
                .getSetResult().getSecondTeamSetResult().setPoints(18);

        match.getSets().stream().filter(set -> set.getSetNumber() == 2).findFirst().get()
                .getSetResult().getFirstTeamSetResult().setPoints(11);
        match.getSets().stream().filter(set -> set.getSetNumber() == 2).findFirst().get()
                .getSetResult().getSecondTeamSetResult().setPoints(21);

        match.getSets().stream().filter(set -> set.getSetNumber() == 3).findFirst().get()
                .getSetResult().getFirstTeamSetResult().setPoints(15);
        match.getSets().stream().filter(set -> set.getSetNumber() == 3).findFirst().get()
                .getSetResult().getSecondTeamSetResult().setPoints(11);

        Team winner = match.getWinner().orElseThrow(ValidationException::new);
        Team loser = match.getLoser().orElseThrow(ValidationException::new);

        assertThat(winner).isEqualTo(r1);
        assertThat(loser).isEqualTo(r16);
    }

}

