package com.tom.test.repositories;

import com.tom.test.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tom on 6/21/2016.
 */
public interface PublisherRepository extends CrudRepository<Publisher,Integer> {
}
