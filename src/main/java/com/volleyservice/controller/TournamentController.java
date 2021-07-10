package com.volleyservice.controller;

import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.mapper.TeamMapper;
import com.volleyservice.mapper.TournamentMapper;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import com.volleyservice.to.TournamentRequestTO;
import com.volleyservice.to.TournamentTO;
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

    @GetMapping("/tournaments/{id}")
    ResponseEntity<EntityModel<TournamentTO>> findOne(@PathVariable long id) {
        return tournamentService.findById(id).map(tournament -> EntityModel.of(tournamentMapper.mapsToTO(tournament),
                linkTo(methodOn(TournamentController.class).findOne(tournament.getId())).withSelfRel(),
                linkTo(methodOn(TournamentController.class).findAll()).withRel("tournaments"))).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tournaments")
    ResponseEntity<?> newTournament(@RequestBody TournamentRequestTO tournamentRequestTO) {
        Tournament savedTournament = tournamentService.save(tournamentMapper.mapsToEntity(tournamentRequestTO));

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


    @GetMapping("/tournament/{tournamentId}/teams/{teamId}")
    ResponseEntity<EntityModel<Team>> findOneTeam(@PathVariable long tournamentId,
                                                  @PathVariable long teamId) {
        return tournamentService.findOneTeamInTournamentById(tournamentId, teamId)
                .map(team -> EntityModel.of((team),
                linkTo(methodOn(TournamentController.class).findOneTeam(tournamentId, team.getId())).withSelfRel(),
                linkTo(methodOn(TournamentController.class).findAllTeamsInTournament(tournamentId))
                        .withRel("teams"))).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tournament/{tournamentId}/teams")
    ResponseEntity<?> newTeamInTournament(@PathVariable long tournamentId,
                                          @RequestBody @Validated TeamRequestTO teamRequestTO) {
        Team savedTeam = tournamentService.saveTeamIntoTournament(tournamentId, teamMapper.mapsToEntity(teamRequestTO));

        EntityModel<Team> teamResource = EntityModel.of(savedTeam,
                linkTo(methodOn(TournamentController.class).findOneTeam(tournamentId, savedTeam.getId())).withSelfRel());

        try {
            return ResponseEntity.created(new URI(teamResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(teamResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create" + teamRequestTO);
        }
    }
}
