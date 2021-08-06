package com.volleyservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volleyservice.entity.Tournament;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.mapper.TournamentMapper;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;
    private final ObjectMapper objectMapper;

    @GetMapping("/tournaments")
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

    @PostMapping("/tournaments")
    ResponseEntity<?> newTournament(@RequestBody TournamentRequestTO tournamentRequestTO) {
        Tournament savedTournament = tournamentService.save(tournamentMapper.mapsTournamentRequestTOToEntity(tournamentRequestTO));

        EntityModel<TournamentTO> tournamentResource = EntityModel.of(
                tournamentMapper.mapsToTO(savedTournament),
                linkTo(methodOn(TournamentController.class).findOne(savedTournament.getId())).withSelfRel()
        );
        try {
            return ResponseEntity
                    .created(new URI(tournamentResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(tournamentResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create" + tournamentRequestTO);
        }
    }

    @GetMapping("/tournaments/{id}")
    ResponseEntity<EntityModel<TournamentTO>> findOne(@PathVariable long id) {
        return tournamentService.findById(id).map(tournament -> EntityModel.of(tournamentMapper.mapsToTO(tournament),
                linkTo(methodOn(TournamentController.class).findOne(tournament.getId())).withSelfRel(),
                linkTo(methodOn(TournamentController.class).findAll()).withRel("tournaments"))).map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Tournament does not exist"));
    }

    @PatchMapping(path = "/tournaments/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<TournamentTO> updateTournament(@PathVariable long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Tournament tournamentToPatch = tournamentService.findById(id).orElseThrow(IllegalArgumentException::new);


        Tournament tournamentPatched = applyPatchToTournament(patch, tournamentToPatch);
        tournamentService.save(tournamentPatched);
        return ResponseEntity.ok(tournamentMapper.mapsToTO(tournamentPatched));

    }

    private Tournament applyPatchToTournament(JsonPatch patch, Tournament targetTournament)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTournament, JsonNode.class));
        return objectMapper.treeToValue(patched, Tournament.class);
    }

}
