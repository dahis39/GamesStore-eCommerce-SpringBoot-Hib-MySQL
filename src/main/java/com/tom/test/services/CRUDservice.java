package com.tom.test.services;

import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.DomainObject;

import java.util.List;

/**
 * Created by tom on 6/8/2016.
 */
public interface CRUDservice<T> {
    List<AbstartDomainClass> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
