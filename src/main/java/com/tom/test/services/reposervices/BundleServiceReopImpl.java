package com.tom.test.services.reposervices;

import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.Bundle;
import com.tom.test.repositories.BundleRepository;
import com.tom.test.repositories.ProductRepository;
import com.tom.test.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/11/2016.
 */
@Service
public class BundleServiceReopImpl implements BundleService {

    @Autowired
    private BundleRepository bundleRepository;

    @Override
    @Cacheable(cacheNames = "bundles")
    public List<?> listAll() {
        List<Bundle> bundles = new ArrayList<>();
        bundleRepository.findAll().forEach(bundles::add);
        return bundles;
    }

    @Override
    @Cacheable(cacheNames = "bundle", key = "#id")
    public Bundle getById(Integer id) {
        return bundleRepository.findOne(id);
    }

    @Override
    @CachePut(cacheNames = "bundle", key = "#result.getId()")
    @CacheEvict(cacheNames = "bundles", allEntries = true)
    public Bundle saveOrUpdate(Bundle domainObject) {
        return bundleRepository.save(domainObject);
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "bundles", allEntries = true), @CacheEvict(cacheNames = "bundle", key = "#id")})
    public void delete(Integer id) {
        bundleRepository.delete(id);
    }
}
