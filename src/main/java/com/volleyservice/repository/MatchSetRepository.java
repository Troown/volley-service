package com.volleyservice.repository;

import com.volleyservice.entity.MatchSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchSetRepository extends CrudRepository<MatchSet, Long> {
}
