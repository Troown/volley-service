package com.volleyservice.controller;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerRepository repository;

    PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/players")
    Player newPlayer(@RequestBody Player newPlayer) {
        return repository.save(newPlayer);
    }

    @GetMapping("/players")
    List<Player> all() {
        return (List<Player>) repository.findAll();
    }


}
