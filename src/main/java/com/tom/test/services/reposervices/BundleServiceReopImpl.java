package com.tom.test.services.reposervices;

import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.Bundle;
import com.tom.test.repositories.BundleRepository;
import com.tom.test.repositories.ProductRepository;
import com.tom.test.services.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AbstartDomainClass> listAll() {
        List<AbstartDomainClass> bundles = new ArrayList<>();
        bundleRepository.findAll().forEach(bundles::add);
        return bundles;
    }

    @Override
    public Bundle getById(Integer id) {
        return bundleRepository.findOne(id);
    }

    @Override
    public Bundle saveOrUpdate(Bundle domainObject) {
        return bundleRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        bundleRepository.delete(id);
    }
}
