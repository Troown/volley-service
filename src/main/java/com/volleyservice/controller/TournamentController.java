package com.volleyservice.controller;

import com.volleyservice.entity.Tournament;
import com.volleyservice.exception.ControllerHelper;
import com.volleyservice.mapper.TournamentMapper;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;
    private final ControllerHelper controllerHelper;

    @GetMapping("/")
    ResponseEntity<CollectionModel<EntityModel<TournamentTO>>> findAll() {
        List<EntityModel<TournamentTO>> tournaments = tournamentService.findAll().stream()
                .map(tournament -> EntityModel.of(tournamentMapper.mapsToTO(tournament),
                        linkTo(methodOn(TournamentController.class).findOne(tournament.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                CollectionModel.of(tournaments,
                        linkTo(methodOn(TournamentController.class).findAll()).withSelfRel())
        );
    }

    @PostMapping("/")
    ResponseEntity<?> newTournament(@RequestBody TournamentRequestTO tournamentRequestTO) {
        Tournament savedTournament = tournamentService.save(tournamentMapper.mapsToEntity(tournamentRequestTO));

        EntityModel<TournamentTO> tournamentResource = EntityModel.of(
                tournamentMapper.mapsToTO(savedTournament),
                linkTo(methodOn(TournamentController.class).findOne(savedTournament.getId())).withSelfRel()
        );
        return controllerHelper.createOrReturnBadRequest(tournamentResource);
    }

    @GetMapping("/{id}")
    ResponseEntity<TournamentTO> findOne(@PathVariable long id) {
        return ResponseEntity.ok(tournamentMapper.mapsToTO(tournamentService.findById(id)));
    }

}
