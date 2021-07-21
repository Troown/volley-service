package com.volleyservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.TeamMapper;
import com.volleyservice.mapper.TournamentMapper;
import com.volleyservice.service.TeamValidator;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.*;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    private final TeamMapper teamMapper;
    private final ObjectMapper objectMapper;
    private final TeamValidator teamValidator;

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
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(path = "/tournament/{tournamentId}", consumes = "application/json-patch+json")
    public ResponseEntity<TournamentTO> updateTournament(@PathVariable long tournamentId, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Tournament tournamentToPatch = tournamentService.findById(tournamentId).orElseThrow(IllegalArgumentException::new);


        Tournament tournamentPatched = applyPatchToTournament(patch, tournamentToPatch);
        tournamentService.save(tournamentPatched);
        return ResponseEntity.ok(tournamentMapper.mapsToTO(tournamentPatched));

    }

    @GetMapping("/tournament/{tournamentId}/teams")
    ResponseEntity<CollectionModel<EntityModel<TeamTO>>> findAllTeamsInTournament(@PathVariable long tournamentId) {
        List<EntityModel<TeamTO>> teams = tournamentService.findAllTeamsInTournament(tournamentId)
                .stream().map(team -> EntityModel.of(teamMapper.mapsToTO(team),
                        linkTo(methodOn(TournamentController.class)
                                .findOneTeam(tournamentId, team.getId())).withSelfRel())).collect(Collectors.toList());
        return ResponseEntity.ok(
                CollectionModel.of(teams,
                        linkTo(methodOn(TournamentController.class)
                                .findAllTeamsInTournament(tournamentId)).withSelfRel()));
    }

    @PostMapping("/tournament/{tournamentId}/teams")
    ResponseEntity<?> newTeamInTournament(@PathVariable long tournamentId,
                                                   @RequestBody @Validated TeamRequestTO teamRequestTO) {

        Tournament updatedTournament = tournamentService
                .saveTeamIntoTournament(tournamentId, teamValidator.validateTeam(tournamentId, teamRequestTO));

        EntityModel<TournamentTO> tournamentResource = EntityModel.of(
                tournamentMapper.mapsToTO(updatedTournament),
                linkTo(methodOn((TournamentController.class)).findOne(updatedTournament.getId())).withSelfRel()
        );

        try {
            return ResponseEntity
                    .created(new URI(tournamentResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(tournamentResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("unable to create");
        }



    }

    @GetMapping("/tournament/{tournamentId}/teams/{teamId}")
    ResponseEntity<EntityModel<Team>> findOneTeam(@PathVariable long tournamentId,
                                                  @PathVariable long teamId) {
        return tournamentService.findOneTeamInTournamentById(tournamentId, teamId)
                .map(team -> EntityModel.of((team),
                linkTo(methodOn(TournamentController.class).findOneTeam(tournamentId, team.getId())).withSelfRel(),
                linkTo(methodOn(TournamentController.class).findAllTeamsInTournament(tournamentId))
                        .withRel("teams"))).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    private Tournament applyPatchToTournament(JsonPatch patch, Tournament targetTournament)
            throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTournament, JsonNode.class));
        return objectMapper.treeToValue(patched, Tournament.class);
    }

}
