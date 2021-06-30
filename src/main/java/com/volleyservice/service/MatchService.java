package com.volleyservice.service;

import com.volleyservice.entity.Match;
import com.volleyservice.entity.Player;
import com.volleyservice.repository.MatchRepository;
import com.volleyservice.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchRepository repository;

    public List<Match> findAl() {
        return (List<Match>) repository.findAll();
    }

    public Match save(Match match) {
        return repository.save(match);
    }

    public Optional<Match> findById(long id) {
        return this.repository.findById(id);
    }
}
