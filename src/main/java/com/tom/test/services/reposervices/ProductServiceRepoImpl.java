package com.tom.test.services.reposervices;

import com.tom.test.domain.Bundle;
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
        Developer codeForce = new Developer();
        codeForce.setName("Code Force");
        Developer valveDev = new Developer();
        valveDev.setName("Valve Corporation");
        Developer taleWorlds = new Developer();
        taleWorlds.setName("TaleWorlds Entertainment");
        Developer paradoxDev = new Developer();
        paradoxDev.setName("Paradox Development Studio");
        Developer obsidian = new Developer();
        obsidian.setName("Obsidian Entertainment");
        Developer bethesdaDev = new Developer();
        bethesdaDev.setName("Bethesda Game Studios");

        Publisher matrixGames =  new Publisher();
        matrixGames.setName("Matrix Games");
        Publisher valvePub = new Publisher();
        valvePub.setName("Valve Corporation");
        Publisher paradoxPub = new Publisher();
        paradoxPub.setName("Paradox Interactive");
        Publisher bethesdaPub = new Publisher();
        bethesdaPub.setName("Bethesda Softworks");

        Bundle popluarMulti = new Bundle();
        popluarMulti.setName("Popular Multiplayers");

        Bundle rpgs = new Bundle();
        rpgs.setName("Role Playing Games");

        productRepository.save(productGenerator("Distant Worlds","Space 4X",new BigDecimal(10),codeForce,matrixGames));
        productRepository.save(productGenerator("Distant Worlds Universe","Space 4X",new BigDecimal(50),codeForce,matrixGames));
        productRepository.save(bundleBinding(popluarMulti, productGenerator("Counter-Strike","FPS",new BigDecimal(10),valveDev,valvePub)));
        productRepository.save(bundleBinding(popluarMulti, productGenerator("Dota 2","RTS",new BigDecimal(0),valveDev,valvePub)));
        productRepository.save(bundleBinding(rpgs, bundleBinding(popluarMulti, productGenerator("Mount & Blade","ARPG",new BigDecimal(10),taleWorlds,paradoxPub))));
        productRepository.save(productGenerator("Europa Universalis IV","strategy",new BigDecimal(25),paradoxDev,paradoxPub));
        productRepository.save(productGenerator("Stellaris","Space 4X",new BigDecimal(40),paradoxDev,paradoxPub));
        productRepository.save(bundleBinding(rpgs, productGenerator("Pillars of Eternity","RPG",new BigDecimal(30),obsidian,paradoxPub)));
        productRepository.save(bundleBinding(rpgs, productGenerator("Fallout: New Vegas","ARPG",new BigDecimal(20),obsidian,bethesdaPub)));
        productRepository.save(bundleBinding(rpgs, productGenerator("The Elder Scrolls V: Skyrim","ARPG",new BigDecimal(30),bethesdaDev,bethesdaPub)));
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

        return newProduct;
    }

    private Product bundleBinding(Bundle bundle, Product product){
        product.addBundle(bundle);
        bundle.addProduct(product);
        return product;
    }
}
