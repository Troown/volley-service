package com.volleyservice.controller;

import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.entity.Team;
import com.volleyservice.mapper.TeamMapper;
import com.volleyservice.service.TeamService;
import com.volleyservice.to.TeamRequestTO;
import com.volleyservice.to.TeamTO;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@RestController
public class TeamController {
    private final PlayerRepository playerRepository;
    private final TeamService teamService;
    private final TeamMapper teamMapper;

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

    @PostMapping("/teams")
    ResponseEntity<?> newTeam(@RequestBody @Validated TeamRequestTO teamRequestTO) {
        Team savedTeam = teamService.save(teamMapper.mapsToEntity(playerRepository, teamRequestTO));
        EntityModel<Team> teamResource = EntityModel.of(savedTeam,
        linkTo(methodOn(TeamController.class).findOne(savedTeam.getId())).withSelfRel());
        try {
            return ResponseEntity.created(new URI(teamResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
                    .body(teamResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create" + teamRequestTO);
        }
    }

    @GetMapping("/teams/{id}")
    ResponseEntity<EntityModel<Team>> findOne(@PathVariable long id) {
        return teamService.findById(id).map(team -> EntityModel.of((team),
                linkTo(methodOn(TeamController.class).findOne(team.getId())).withSelfRel(),
                linkTo(methodOn(TeamController.class).findAll()).withRel("teams"))).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }
}

