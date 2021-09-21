package com.volleyservice.controller;

import com.volleyservice.entity.Player;

import com.volleyservice.mapper.PlayerMapper;
import com.volleyservice.service.PlayerService;
import com.volleyservice.to.PlayerRequestTO;
import com.volleyservice.to.PlayerTO;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    @GetMapping("/")
    ResponseEntity<List<PlayerTO>> findAll() {
        return ResponseEntity.ok(playerService.findAll().stream().map(playerMapper::mapsToTO)
                .collect(toList()));
    }
    @PostMapping("/")
    ResponseEntity<PlayerTO> newPlayer(@RequestBody @Validated PlayerRequestTO playerRequestTO) {
        Player savedPlayer = playerService.save(playerMapper.mapsToEntity(playerRequestTO));
        URI location = URI.create(String.format("/players/%s", savedPlayer.getId()));
        return ResponseEntity.created(location).body(playerMapper.mapsToTO(savedPlayer));
    }

    @GetMapping("/{id}")
    ResponseEntity<PlayerTO> findOne(@PathVariable long id) {
        return ResponseEntity.ok(playerMapper.mapsToTO(playerService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.delete(id);
    }
}
