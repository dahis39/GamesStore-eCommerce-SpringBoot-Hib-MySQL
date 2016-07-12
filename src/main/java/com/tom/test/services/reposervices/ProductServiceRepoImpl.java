package com.tom.test.services.reposervices;

import com.tom.test.domain.*;
import com.tom.test.repositories.ProductRepository;
import com.tom.test.services.BundleService;
import com.tom.test.services.ProductService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
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
    @Autowired
    private BundleService bundleService;

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
        Product product = productRepository.findOne(id);
        product.getBundles().forEach(bundle -> {
            bundle.deleteProduct(product);
            bundleService.saveOrUpdate(bundle);
        });
        productRepository.delete(id);
    }
}
