package com.tom.test.services;

import com.tom.test.domain.Bundle;
import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by tom on 7/6/2016.
 */
@Service
public class DummyDataGeneration {

    @Autowired
    ProductService productService;
    @Autowired
    DeveloperService developerService;
    @Autowired
    PublisherService publisherService;
    @Autowired
    BundleService bundleService;

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

        Developer nullDev = new Developer();
        nullDev.setName("Default Developer");
        nullDev.setId(1);
        developerService.saveOrUpdate(nullDev);

        Publisher nullPub = new Publisher();
        nullPub.setName("Default Publisher");
        nullPub.setId(1);
        publisherService.saveOrUpdate(nullPub);

//        Bundle nullBundle = new Bundle();
//        nullBundle.setName("Default Bundle");
//        nullBundle.setId(1);
//        bundleService.saveOrUpdate(nullBundle);

        productService.saveOrUpdate(productGenerator("Distant Worlds","Space 4X",new BigDecimal(10),codeForce,matrixGames));
        productService.saveOrUpdate(productGenerator("Distant Worlds Universe","Space 4X",new BigDecimal(50),codeForce,matrixGames));
        productService.saveOrUpdate(bundleBinding(popluarMulti, productGenerator("Counter-Strike","FPS",new BigDecimal(10),valveDev,valvePub)));
        productService.saveOrUpdate(bundleBinding(popluarMulti, productGenerator("Dota 2","RTS",new BigDecimal(0),valveDev,valvePub)));
        productService.saveOrUpdate(bundleBinding(rpgs, bundleBinding(popluarMulti, productGenerator("Mount & Blade","ARPG",new BigDecimal(10),taleWorlds,paradoxPub))));
        productService.saveOrUpdate(productGenerator("Europa Universalis IV","strategy",new BigDecimal(25),paradoxDev,paradoxPub));
        productService.saveOrUpdate(productGenerator("Stellaris","Space 4X",new BigDecimal(40),paradoxDev,paradoxPub));
        productService.saveOrUpdate(bundleBinding(rpgs, productGenerator("Pillars of Eternity","RPG",new BigDecimal(30),obsidian,paradoxPub)));
        productService.saveOrUpdate(bundleBinding(rpgs, productGenerator("Fallout: New Vegas","ARPG",new BigDecimal(20),obsidian,bethesdaPub)));
        productService.saveOrUpdate(bundleBinding(rpgs, productGenerator("The Elder Scrolls V: Skyrim","ARPG",new BigDecimal(30),bethesdaDev,bethesdaPub)));
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
