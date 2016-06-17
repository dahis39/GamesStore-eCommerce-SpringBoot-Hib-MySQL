package com.tom.test.services.reposervices;

import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
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
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
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

    @Override
    public void generateProduct() {
        Developer developer = new Developer();
        developer.setName("Code Force");
        Publisher publisher =  new Publisher();
        publisher.setName("Matrix Games");
        productRepository.save(productGenerator("Distant Worlds","Original",new BigDecimal(40),developer,publisher));
        productRepository.save(productGenerator("Distant Worlds Universe","BIGBIGBIG",new BigDecimal(20),developer,publisher));
    }

    private Product productGenerator(String name, String description, BigDecimal price, Developer developer, Publisher publisher){
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);

        developer.addProduct(newProduct);

        publisher.addProduct(newProduct);

        newProduct.setDeveloper(developer);
        newProduct.setPublisher(publisher);

        System.out.println(publisher.getProducts().size());
        System.out.println(developer.getProducts().size());

        return newProduct;
    }
}
