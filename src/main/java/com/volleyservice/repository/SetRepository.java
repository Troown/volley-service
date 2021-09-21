package com.volleyservice.repository;

import com.volleyservice.entity.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends CrudRepository<Set, Long> {
}
