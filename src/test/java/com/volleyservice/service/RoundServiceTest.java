package com.volleyservice.service;

import com.volleyservice.SystemDataProvider.DoubleElimination;
import com.volleyservice.entity.Match;
import com.volleyservice.entity.Round;
import com.volleyservice.entity.Tournament;
import com.volleyservice.enums.Phase;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.repository.TournamentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class RoundServiceTest {

    private final TournamentRepository tournamentRepository =
            Mockito.mock(TournamentRepository.class);

    private final DoubleElimination dE = Mockito.mock(DoubleElimination.class);

    private final RoundService roundService = new RoundService(tournamentRepository, dE);

    @Test
    void shouldReturnAllRoundsFromTournament() {
//        given
        long tournamentId = 3;
        Phase myPhase = Phase.QUOTER_FINAL;
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
                                new Match(6),
                                new Match(7))),
                        new Round(3, Phase.QUOTER_FINAL, List.of(
                                new Match(8),
                                new Match(9),
                                new Match(10),
                                new Match(11),
                                new Match(12)))));
        Mockito.when(tournamentRepository.findById(tournamentId))
                .thenReturn(Optional.of(tournament));
//        when
        List<Round> result = roundService.findAlInTournament(tournamentId);
//        them
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void shouldReturnEmptyListWhenNoRoundsSet() {
//        given
        long tournamentId = 3;
        Tournament tournament = new Tournament("My Cup");
        Mockito.when(tournamentRepository.findById(tournamentId))
                .thenReturn(Optional.of(tournament));
//        when
        List<Round> result = roundService.findAlInTournament(tournamentId);
//        them
        assertThat(result).isEmpty();
    }


    @Test
    void shouldFindRoundByPhase() {
//        given
        long tournamentId = 3;
        Phase myPhase = Phase.QUOTER_FINAL;
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
                                new Match(6),
                                new Match(7))),
                        new Round(3, Phase.QUOTER_FINAL, List.of(
                                new Match(8),
                                new Match(9),
                                new Match(10),
                                new Match(11),
                                new Match(12)))
                )
        );
        Mockito.when(tournamentRepository.findById(tournamentId))
                .thenReturn(Optional.of(tournament));

//        when
        Round result = roundService.findByPhase(tournamentId, myPhase);

//        then
        assertThat(result.getMatches().size()).isEqualTo(5);
        assertThat(result.getPhase()).isEqualTo(myPhase);
        assertThat(result.getRoundNumber()).isEqualTo(3);
        assertThat(result.getMatches().stream().map(Match::getMatchNumber).collect(Collectors.toList()))
                .containsAll(List.of(8, 9, 10, 11, 12));

    }

    @Test
    void shouldThrowNotFoundException() {
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
                                new Match(6),
                                new Match(7))),
                        new Round(3, Phase.QUOTER_FINAL, List.of(
                                new Match(8),
                                new Match(9),
                                new Match(10),
                                new Match(11),
                                new Match(12)))
                )
        );
        Mockito.when(tournamentRepository.findById(tournamentId))
                .thenReturn(Optional.of(tournament));

//        when
        Throwable thrown = catchThrowable(() -> roundService.findByPhase(tournamentId, Phase.GOLD_MEDAL));

//        then
        assertThat(thrown).isInstanceOf(NotFoundException.class)
                .hasMessage("Round does not exist");
    }
}