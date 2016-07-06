package com.tom.test.services.reposervices;

import com.tom.test.domain.*;
import com.tom.test.repositories.ProductRepository;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/9/2016.
 */
@Service
public class ProductServiceRepoImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<AbstartDomainClass> listAll() {
        List<AbstartDomainClass> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }

}
