package com.tom.test.bootstrap;

import com.tom.test.domain.Bundle;
import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
import com.tom.test.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by tom on 7/12/2016.
 */
//@Component
//public class DummyDataBootstrap implements ApplicationListener<ContextRefreshedEvent>{
//
//    @Autowired
//    ProductService productService;
//    @Autowired
//    DeveloperService developerService;
//    @Autowired
//    PublisherService publisherService;
//    @Autowired
//    BundleService bundleService;
//
//    @Autowired
//    DummyDataGeneration dummyDataGeneration;
//
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
////        dummyDataGeneration.generateProduct();
//
////        generateProducts();
////        generateAndAssignDevelopers();
////        generateAndAssignPublishers();
////        generateAndAssignBundles();
//    }
//
//    public void generateProducts() {
//
//
//
//        productService.saveOrUpdate(productGenerator("Distant Worlds","Space 4X",new BigDecimal(10)));
//        productService.saveOrUpdate(productGenerator("Distant Worlds Universe","Space 4X",new BigDecimal(50)));
//        productService.saveOrUpdate(productGenerator("Counter-Strike","FPS",new BigDecimal(10)));
//        productService.saveOrUpdate(productGenerator("Dota 2","RTS",new BigDecimal(0)));
//        productService.saveOrUpdate(productGenerator("Mount & Blade","ARPG",new BigDecimal(10)));
//        productService.saveOrUpdate(productGenerator("Europa Universalis IV","strategy",new BigDecimal(25)));
//        productService.saveOrUpdate(productGenerator("Stellaris","Space 4X",new BigDecimal(40)));
//        productService.saveOrUpdate(productGenerator("Pillars of Eternity","RPG",new BigDecimal(30)));
//        productService.saveOrUpdate(productGenerator("Fallout: New Vegas","ARPG",new BigDecimal(20)));
//        productService.saveOrUpdate(productGenerator("The Elder Scrolls V: Skyrim","ARPG",new BigDecimal(30)));
//    }
//
//    private void generateAndAssignDevelopers(){
//        Developer nullDev = new Developer();
//        nullDev.setName("Unknown Developer");
//        nullDev.setDescription("To be assigned");
//        nullDev.setId(1);
//        developerService.saveOrUpdate(nullDev);
//
//        Developer codeForce = new Developer();
//        codeForce.setName("Code Force");
//        developerService.saveOrUpdate(codeForce);
//        codeForce.addProduct(productService.getById(1));
//        codeForce.addProduct(productService.getById(2));
//        developerService.saveOrUpdate(codeForce);
//
//        Developer valveDev = new Developer();
//        valveDev.setName("Valve Corporation");
//        developerService.saveOrUpdate(valveDev);
//        valveDev.addProduct(productService.getById(3));
//        valveDev.addProduct(productService.getById(4));
//        developerService.saveOrUpdate(valveDev);
//
//        Developer taleWorlds = new Developer();
//        taleWorlds.setName("TaleWorlds Entertainment");
//        developerService.saveOrUpdate(taleWorlds);
//        taleWorlds.addProduct(productService.getById(5));
//        developerService.saveOrUpdate(taleWorlds);
//
//        Developer paradoxDev = new Developer();
//        paradoxDev.setName("Paradox Development Studio");
//        developerService.saveOrUpdate(paradoxDev);
//        paradoxDev.addProduct(productService.getById(6));
//        paradoxDev.addProduct(productService.getById(7));
//        developerService.saveOrUpdate(paradoxDev);
//
//        Developer obsidian = new Developer();
//        obsidian.setName("Obsidian Entertainment");
//        developerService.saveOrUpdate(obsidian);
//        obsidian.addProduct(productService.getById(8));
//        developerService.saveOrUpdate(obsidian);
//
//        Developer bethesdaDev = new Developer();
//        bethesdaDev.setName("Bethesda Game Studios");
//        developerService.saveOrUpdate(bethesdaDev);
//        bethesdaDev.addProduct(productService.getById(9));
//        bethesdaDev.addProduct(productService.getById(10));
//        developerService.saveOrUpdate(bethesdaDev);
//    }
//
//    private void generateAndAssignPublishers(){
//        Publisher nullPub = new Publisher();
//        nullPub.setName("Unknown Publisher");
//        nullPub.setDescription("To be assgined.");
//        nullPub.setId(1);
//        publisherService.saveOrUpdate(nullPub);
//
//        Publisher matrixGames = new Publisher();
//        matrixGames.setName("Matrix Games");
//        publisherService.saveOrUpdate(matrixGames);
//        matrixGames = publisherService.getById(2);
//        matrixGames.addProduct(productService.getById(1));
//        matrixGames.addProduct(productService.getById(2));
//        publisherService.saveOrUpdate(matrixGames);
//
//        Publisher valvePub = new Publisher();
//        valvePub.setName("Valve Corporation");
//        publisherService.saveOrUpdate(valvePub);
//        valvePub = publisherService.getById(3);
//        valvePub.addProduct(productService.getById(3));
//        valvePub.addProduct(productService.getById(4));
//        publisherService.saveOrUpdate(valvePub);
//
//        Publisher paradoxPub = new Publisher();
//        paradoxPub.setName("Paradox Interactive");
//        publisherService.saveOrUpdate(paradoxPub);
//        paradoxPub = publisherService.getById(4);
//        paradoxPub.addProduct(productService.getById(5));
//        paradoxPub.addProduct(productService.getById(6));
//        paradoxPub.addProduct(productService.getById(7));
//        paradoxPub.addProduct(productService.getById(8));
//        publisherService.saveOrUpdate(paradoxPub);
//
//        Publisher bethesdaPub = new Publisher();
//        bethesdaPub.setName("Bethesda Softworks");
//        publisherService.saveOrUpdate(bethesdaPub);
//        bethesdaPub = publisherService.getById(4);
//        bethesdaPub.addProduct(productService.getById(9));
//        bethesdaPub.addProduct(productService.getById(10));
//        publisherService.saveOrUpdate(bethesdaPub);
//    }
//
//    private void generateAndAssignBundles(){
//        Bundle multiplayer = new Bundle();
//        multiplayer.setName("Popular Multiplayer Games");
//        multiplayer.setPrice(new BigDecimal(15));
//        bundleService.saveOrUpdate(multiplayer);
//
//        multiplayer.addProduct(productService.getById(3));
//        multiplayer.addProduct(productService.getById(4));
//        multiplayer.addProduct(productService.getById(5));
//        bundleService.saveOrUpdate(multiplayer);
//
//        Bundle rpg = new Bundle();
//        rpg.setName("RPG games");
//        rpg.setPrice(new BigDecimal(60));
//        bundleService.saveOrUpdate(rpg);
//
//        rpg.addProduct(productService.getById(5));
//        rpg.addProduct(productService.getById(8));
//        rpg.addProduct(productService.getById(9));
//        rpg.addProduct(productService.getById(10));
//        bundleService.saveOrUpdate(rpg);
//    }
//
//    private Product productGenerator(String name, String description, BigDecimal price){
//        Product newProduct = new Product();
//        newProduct.setName(name);
//        newProduct.setDescription(description);
//        newProduct.setPrice(price);
//
//        return newProduct;
//    }
//}
