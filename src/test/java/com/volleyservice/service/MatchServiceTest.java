package com.volleyservice.service;

import com.volleyservice.entity.*;
import com.volleyservice.enums.Phase;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class MatchServiceTest {
    @Mock
    private Team r1;
    @Mock
    private Team r16;
    @Mock
    private Team r2;

    @Mock
    private TournamentRepository repo;
    @InjectMocks
    private MatchService matchService;

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

        when(repo.findById(3L))
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

        when(repo.findById(tournamentId))
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

        when(repo.findById(tournamentId))
                .thenReturn(Optional.of(tournament));
//        when
        List<Match> result = matchService.findAlInTournament(3L);
//        then
        assertThat(result.size()).isEqualTo(6);
    }
}