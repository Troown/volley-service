package com.volleyservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import com.volleyservice.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;

    private final PlayerMapper playerMapper;

    public List<Player> findAll () {
        return (List<Player>) repository.findAll();
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Player> findById(long id) {
        return repository.findById(id);
    }

}
