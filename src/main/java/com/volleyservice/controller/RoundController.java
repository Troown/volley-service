package com.volleyservice.controller;


import com.volleyservice.entity.Round;
import com.volleyservice.repository.TournamentRepository;
import com.volleyservice.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoundController {
    private final RoundService roundService;

    @PostMapping("/tournaments/{id}/rounds")
    public List<Round> newRounds(@PathVariable long id) {
        return roundService.createAllRounds(id).getRounds();
    }
}
