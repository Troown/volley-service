package com.volleyservice.controller;

import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.RoundMapper;
import com.volleyservice.service.RoundService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RoundControllerTest {

    private final RoundService roundService =
            Mockito.mock(RoundService.class);

    private final RoundMapper roundMapper =
            Mockito.mock(RoundMapper.class);

    RoundController roundController = new RoundController(roundService, roundMapper);
    @Test
    void shouldReturnStatusOkWhenRoundsNotSet() {
//        given
        Tournament tournament = new Tournament("My CUP");
        Mockito.when(roundService.findAlInTournament(3L)).thenReturn(tournament.getRounds());
//        when
        var result = roundController.findAll(3L);

//        then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}