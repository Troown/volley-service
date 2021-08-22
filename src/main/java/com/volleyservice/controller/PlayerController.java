package com.volleyservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volleyservice.entity.Player;

import com.volleyservice.exception.ControllerHelper;
import com.volleyservice.exception.NotFoundException;
import com.volleyservice.mapper.PlayerMapper;
import com.volleyservice.service.PlayerService;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;
    private final ControllerHelper controllerHelper;

    @GetMapping("/")
    ResponseEntity<CollectionModel<EntityModel<PlayerTO>>> findAll() {
        List<EntityModel<PlayerTO>> players = playerService.findAll().stream()
                .map(player -> EntityModel.of(playerMapper.mapsToTO(player),
                        linkTo(methodOn(PlayerController.class).findOne(player.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(players,
                        linkTo(methodOn(PlayerController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/")
    ResponseEntity<?> newPlayer(@RequestBody @Validated PlayerRequestTO playerRequestTO) {

        Player savedPlayer = playerService.save(playerMapper.mapsToEntity(playerRequestTO));

        EntityModel<PlayerTO> playerResource = EntityModel.of(playerMapper.mapsToTO(savedPlayer), //
                linkTo(methodOn(PlayerController.class).findOne(savedPlayer.getId())).withSelfRel(),
                linkTo(methodOn(PlayerController.class).findAll()).withRel("players"));
        return controllerHelper.tryCreateOrReturnBadRequest(playerResource);
    }

    @GetMapping("/{id}")
    ResponseEntity<PlayerTO> findOne(@PathVariable long id) {
        return ResponseEntity.ok(playerMapper.mapsToTO(playerService.findById(id)));
    }

    @DeleteMapping("players/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
