package com.tom.test.services.reposervices;

import com.tom.test.commands.ProductForm;
import com.tom.test.converters.ProductFormToProduct;
import com.tom.test.converters.ProductToProductForm;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Autowired
    private ProductFormToProduct productFormToProduct;
    @Autowired
    private ProductToProductForm productToProductForm;

    @Override
    @Cacheable(cacheNames = "products")
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    @Cacheable(cacheNames = "product", key = "#id")
    public Product getById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    @CachePut(cacheNames = "product", key = "#result.getId()")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    @Caching(evict = {@CacheEvict(cacheNames = "products", allEntries = true), @CacheEvict(cacheNames = "product", key = "#id")})
    public void delete(Integer id) {
        Product product = productRepository.findOne(id);
        product.getBundles().forEach(bundle -> {
            bundle.deleteProduct(product);
            bundleService.saveOrUpdate(bundle);
        });
        productRepository.delete(id);
    }

    @Override
    @CachePut(cacheNames = "product", key = "#result.getId()")
    @CacheEvict(cacheNames = "products", allEntries = true)
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        return productRepository.save(productFormToProduct.convert(productForm));
    }

    @Override
    public ProductForm findProductFormById(Integer id) {
        return productToProductForm.convert(productRepository.findOne(id));
    }
}
