package com.volleyservice.service;

import com.volleyservice.entity.Player;
import com.volleyservice.repository.PlayerRepository;
import com.volleyservice.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;

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
