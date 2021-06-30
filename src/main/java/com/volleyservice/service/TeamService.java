package com.volleyservice.service;

import com.volleyservice.entity.Team;
import com.volleyservice.repository.TeamRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class TeamService {
    private final TeamRepository repository;

    public TeamService(TeamRepository repository) {
        this.repository = repository;
    }

    public List<Team> findAll() {
        return (List<Team>) repository.findAll();
    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public Optional<Team> findById(long id) {
        return repository.findById(id);

    }

}
