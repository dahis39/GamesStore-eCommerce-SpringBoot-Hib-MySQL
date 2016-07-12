package com.tom.test.repositories;

import com.tom.test.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by tom on 6/9/2016.
 */
public interface ProductRepository extends CrudRepository<Product,Integer>{
}

