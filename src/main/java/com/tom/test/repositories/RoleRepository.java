package com.tom.test.repositories;

import com.tom.test.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tom on 8/3/2016.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
