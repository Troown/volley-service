package com.volleyservice.repository;

import com.volleyservice.entity.Tournament;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
