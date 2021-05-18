package com.volleyservice.controller;

import com.volleyservice.entity.Player;
import com.volleyservice.mapper.PlayerMapper;
import com.volleyservice.service.PlayerService;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public PlayerController(PlayerMapper playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @GetMapping("/players")
    ResponseEntity<CollectionModel<EntityModel<PlayerTO>>> findAll() {

        List<EntityModel<PlayerTO>> players = StreamSupport.stream(playerService.findAll().spliterator(), false)
                .map(player -> EntityModel.of(playerMapper.mapsToTO(player), //
                        linkTo(methodOn(PlayerController.class).findOne(player.getId())).withSelfRel(), //
                        linkTo(methodOn(PlayerController.class).findAll()).withRel("players"))) //
                .collect(Collectors.toList());

        return ResponseEntity.ok( //
                CollectionModel.of(players, //
                        linkTo(methodOn(PlayerController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/players")
    ResponseEntity<?> newPlayer (@RequestBody @Validated PlayerRequestTO playerRequestTO) {

        try {
            Player savedPlayer = playerService.save(playerMapper.mapsToEntity(playerRequestTO));

            EntityModel<PlayerTO> playerResource = EntityModel.of(playerMapper.mapsToTO(savedPlayer), //
                    linkTo(methodOn(PlayerController.class).findOne(savedPlayer.getId())).withSelfRel());

            return ResponseEntity //
                    .created(new URI(playerResource.getRequiredLink(IanaLinkRelations.SELF).getHref())) //
                    .body(playerResource);
        } catch (URISyntaxException e) {
            return ResponseEntity.badRequest().body("Unable to create" + playerRequestTO); //why in response body message = ""????
        }
    }

    @GetMapping("/players/{id}")
    ResponseEntity<EntityModel<PlayerTO>> findOne(@PathVariable long id) {

        return playerService.findById(id).map(player -> EntityModel.of(playerMapper.mapsToTO(player), //
                        linkTo(methodOn(PlayerController.class).findOne(player.getId())).withSelfRel(), //
                        linkTo(methodOn(PlayerController.class).findAll()).withRel("players"))).map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());

    }
}
