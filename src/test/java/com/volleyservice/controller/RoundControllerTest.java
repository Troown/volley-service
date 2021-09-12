package com.volleyservice.controller;

import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.RoundMapper;
import com.volleyservice.service.RoundService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoundControllerTest {

    @Mock
    private RoundService roundService;
    @Mock
    private RoundMapper roundMapper;
    @InjectMocks
    RoundController roundController;

    @Test
    void shouldReturnStatusOkWhenRoundsNotSet() {
//        given
        Tournament tournament = new Tournament("My CUP");
        when(roundService.findAlInTournament(3L)).thenReturn(tournament.getRounds());
//        when
        var result = roundController.findAll(3L);

//        then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}