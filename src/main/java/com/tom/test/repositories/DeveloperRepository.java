package com.tom.test.repositories;

import com.tom.test.domain.Developer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tom on 6/13/2016.
 */
public interface DeveloperRepository extends CrudRepository<Developer,Integer> {
}
