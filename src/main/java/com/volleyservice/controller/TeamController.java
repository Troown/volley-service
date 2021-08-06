package com.volleyservice.controller;

import com.volleyservice.entity.Team;
import com.volleyservice.entity.Tournament;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.mapper.TeamMapper;
import com.volleyservice.mapper.TournamentMapper;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.service.TeamService;
import com.volleyservice.service.TournamentService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import com.volleyservice.to.TournamentTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;

    @GetMapping("/teams")
    ResponseEntity<CollectionModel<EntityModel<TeamTO>>> findAll() {
        List<EntityModel<TeamTO>> teams = teamService.findAll().stream().
                map(team -> EntityModel.of(teamMapper.mapsToTO(team),
                        linkTo(methodOn(TeamController.class).findOne(team.getId())).withSelfRel())).
                collect(Collectors.toList());
        return ResponseEntity.ok(
                CollectionModel.of(teams,
                        linkTo(methodOn(TeamController.class).findAll()).withSelfRel()));
    }

    @GetMapping("/teams/{id}")
    ResponseEntity<EntityModel<Team>> findOne(@PathVariable long id) {
        return teamService.findById(id).map(team -> EntityModel.of((team),
                linkTo(methodOn(TeamController.class).findOne(team.getId())).withSelfRel(),
                linkTo(methodOn(TeamController.class).findAll()).withRel("teams"))).map(ResponseEntity::ok).
                orElseThrow(() -> new NotFoundException("Team does not exist"));
    }

    @GetMapping("/tournaments/{tournamentId}/teams")
    ResponseEntity<CollectionModel<EntityModel<TeamTO>>> findAllTeamsInTournament(@PathVariable long tournamentId) {

        List<EntityModel<TeamTO>> teams = tournamentService.findAllTeamsInTournament(tournamentId)
                .stream().map(team -> EntityModel.of(teamMapper.mapsToTO(team),
                        linkTo(methodOn(TeamController.class).findOne(team.getId()))
                                .withSelfRel())).collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(
                teams,
                linkTo(methodOn(TeamController.class).findAllTeamsInTournament(tournamentId)).withSelfRel()
        ));
    }

    @GetMapping("/tournament/{tournamentId}/teams/{teamId}")
    ResponseEntity<EntityModel<Team>> findOneInTournament(
            @PathVariable long tournamentId,
            @PathVariable long teamId) {

        return tournamentService.findOneTeamInTournamentById(tournamentId, teamId)
                .map(team -> EntityModel.of((team),
                        linkTo(methodOn(TeamController.class).findOne(team.getId())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).findAllTeamsInTournament(tournamentId))
                                .withRel("teams"))).map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Team does not exist in " + tournamentService
                        .findById(tournamentId).get().getTournamentName()));
    }

    @PostMapping("/tournament/{tournamentId}/teams")
    ResponseEntity<?> newTeamInTournament(@PathVariable long tournamentId,
                                          @RequestBody @Validated TeamRequestTO teamRequestTO) {

        Tournament updatedTournament = tournamentService
                .saveTeamIntoTournament(tournamentId, teamMapper.mapsToEntity(teamRequestTO));

        EntityModel<TournamentTO> tournamentResource = EntityModel.of(
                tournamentMapper.mapsToTO(updatedTournament),
                linkTo(methodOn((TournamentController.class)).findOne(updatedTournament.getId())).withSelfRel(),
                linkTo(methodOn((TeamController.class))
                        .findAllTeamsInTournament(updatedTournament.getId())).withRel("teams")
        );

        try {
            return ResponseEntity
                    .created(new URI(tournamentResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(tournamentResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("unable to create");
        }
    }

}


