package com.volleyservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volleyservice.entity.Player;

import com.volleyservice.mapper.PlayerMapper;
import com.volleyservice.service.PlayerService;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import lombok.AllArgsConstructor;
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
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;
    private final ObjectMapper objectMapper;

    @GetMapping("/players")
    ResponseEntity<CollectionModel<EntityModel<PlayerTO>>> findAll() {
        List<EntityModel<PlayerTO>> players = playerService.findAll().stream()
                .map(player -> EntityModel.of(playerMapper.mapsToTO(player),
                        linkTo(methodOn(PlayerController.class).findOne(player.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(players,
                        linkTo(methodOn(PlayerController.class).findAll()).withSelfRel()));
    }

    @PostMapping("/players")
    ResponseEntity<?> newPlayer(@RequestBody @Validated PlayerRequestTO playerRequestTO) {

        Player savedPlayer = playerService.save(playerMapper.mapsToEntity(playerRequestTO));

        EntityModel<PlayerTO> playerResource = EntityModel.of(playerMapper.mapsToTO(savedPlayer), //
                linkTo(methodOn(PlayerController.class).findOne(savedPlayer.getId())).withSelfRel());
        try {
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


    @PatchMapping(path = "/players/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Player> updatePlayer(@PathVariable long id, @RequestBody JsonPatch patch) {
            Player player = playerService.findById(id).orElseThrow(IllegalArgumentException::new);
        try{
            Player playerPatched = applyPatchToPlayer(patch, player);
            playerService.save(playerPatched);
            return ResponseEntity.ok(playerPatched);

        } catch (JsonPatchException jsonPatchException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (JsonProcessingException jsonProcessingException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @DeleteMapping("players/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }


    private Player applyPatchToPlayer(JsonPatch patch, Player targetPlayer)
            throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetPlayer, JsonNode.class));
        return objectMapper.treeToValue(patched, Player.class);
    }
}
