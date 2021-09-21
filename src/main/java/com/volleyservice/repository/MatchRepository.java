package com.volleyservice.repository;

import com.volleyservice.entity.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
}
