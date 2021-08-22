package com.volleyservice.service;

import com.volleyservice.entity.*;
import com.volleyservice.enums.Phase;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class MatchServiceTest {
    private final Team r1 = new Team(List.of(new Player("Kuba", "Zdybek"),
            new Player("Paweł", "Lewandowski")));

    private final  Team r16 = new Team(List.of(new Player("Mateusz", "Kańczok"),
            new Player("Maciej", "Kałuża")));

    private final TournamentRepository repo =
            Mockito.mock(TournamentRepository.class);
    private final MatchService matchService = new MatchService(repo);

    @Test
    public void shouldFindMatchByNumber() {
//        given
        Tournament tournament = new Tournament("Rybnik Cup");
        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3)))
                )
        );

        Mockito.when(repo.findById(3L))
                .thenReturn(Optional.of(tournament));

//        when
        Match result = matchService.findByMatchNumber(3L, 3);

//        then
        assertThat(result.getMatchNumber()).isEqualTo(3);

    }

    @Test
    public void shouldThrowNotFoundEx() {
//        given
        long tournamentId = 3;
        Tournament tournament = new Tournament("My Cup");
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

        Mockito.when(repo.findById(tournamentId))
                .thenReturn(Optional.of(tournament));

//        when
        Throwable thrown = catchThrowable(() -> matchService.findByMatchNumber(tournamentId, 30));

//        then
        assertThat(thrown).isInstanceOf(NotFoundException.class)
                .hasMessage("Match does not exist"); //TODO use MISSING_MATCH_ERR_MSG
    }

    @Test
    public void shouldFindAllMatchInTournament() {
        //        given
        long tournamentId = 3;
        Tournament tournament = new Tournament("My Cup");
        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3))),
                        new Round(2, Phase.SECOND_ROUND, List.of(
                                new Match(4),
                                new Match(5),
                                new Match(6)))));

        Mockito.when(repo.findById(tournamentId))
                .thenReturn(Optional.of(tournament));
//        when
        List<Match> result = matchService.findAlInTournament(3L);
//        then
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    public void shouldSetResultInGivenMatch() {
//        given
        Tournament tournament = new Tournament("My Cup");
        tournament.setRounds(
                List.of(
                        new Round(1, Phase.FIRST_ROUND, List.of(
                                new Match(1),
                                new Match(2),
                                new Match(3))),
                        new Round(2, Phase.SECOND_ROUND, List.of(
                                new Match(4),
                                new Match(5),
                                new Match(6)))));
        Mockito.when(repo.findById(3L))
                .thenReturn(Optional.of(tournament));
        List<MatchSet> sets = List.of(
                new MatchSet(1, new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))),
                new MatchSet(2, new SetResult(
                        new TeamSetPoint(r1, 21),
                        new TeamSetPoint(r16, 19))));
//        when
        Match result = matchService.saveResult(3, 4, sets);
//        then
        assertThat(result.getSets().size()).isEqualTo(2);
        assertThat(result.getWinner()).isEqualTo(Optional.of(r1));
        assertThat(result.getSets().get(1)
                .getSetResult().getFirstTeamSetResult().getTeam()).isEqualTo(r1);
    }



}