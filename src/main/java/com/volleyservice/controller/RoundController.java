package com.volleyservice.controller;


import com.volleyservice.enums.Phase;
import com.volleyservice.mapper.RoundMapper;
import com.volleyservice.service.RoundService;
import com.volleyservice.to.RoundTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tournaments/{tournamentId}/rounds")
public class RoundController {
    private final RoundService roundService;
    private final RoundMapper roundMapper;

    @PutMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<RoundTO>>> generateRoundsForTournaments(@PathVariable long tournamentId) {
        List<EntityModel<RoundTO>> rounds = roundService.createAllRounds(tournamentId, 16).getRounds().stream()
                .map(round -> EntityModel.of(roundMapper.mapToTO(round),
                        linkTo(methodOn(RoundController.class).findOne(tournamentId, round.getPhase())).withSelfRel()))
                .collect(toList());


        return ResponseEntity.ok(CollectionModel.of(rounds,
                linkTo(methodOn(RoundController.class).findAll(tournamentId)).withSelfRel()));
    }

    @GetMapping("/{phase}")
    public ResponseEntity<RoundTO> findOne(@PathVariable long tournamentId, @PathVariable Phase phase) {
        return ResponseEntity.ok(roundMapper.mapToTO(roundService.findByPhase(tournamentId, phase)));
    }

    @GetMapping("/")
    public ResponseEntity<List<RoundTO>> findAll(@PathVariable long tournamentId) {
        return ResponseEntity.ok(roundService.findAlInTournament(tournamentId)
                .stream().map(roundMapper::mapToTO).collect(toList()));

    }

}
