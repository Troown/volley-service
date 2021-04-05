package com.volleyservice.service;

import com.volleyservice.entity.Player;
import com.volleyservice.entity.PlayerRepository;
import com.volleyservice.mapper.PlayerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper playerMapper=new PlayerMapper();

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> findAll () {
        return (List<Player>) repository.findAll();
    }
    public Player save(Player player) {
        return repository.save(player);
    }

    public Optional<Player> findById(long id) {//why need to be optional?
        return repository.findById(id);
    }
}
