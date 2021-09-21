package com.volleyservice.repository;

import com.volleyservice.entity.Tournament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {
}
