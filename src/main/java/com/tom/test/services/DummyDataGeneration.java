package com.tom.test.services;

import com.tom.test.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    private static boolean initiated = false;

    public void generate(){
        generateProductsAndBundles();
        generateRoles();
        generateUsers();
        assignRolesToUsers();
//        generateCarts();

        initiated = true;
    }

    private void generateCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.saveOrUpdate(user);
        });
    }

    private void generateRoles(){
        Role customer = new Role();
        customer.setRole("CUSTOMER");
        roleService.saveOrUpdate(customer);

        Role admin = new Role();
        admin.setRole("ADMIN");
        roleService.saveOrUpdate(admin);
    }

    private void generateUsers(){
        User user1 = new User();
        user1.setUserName("customer1");
        user1.setEmail("customer1@gmail.com");
        user1.setPassword("123");
        userService.saveOrUpdate(user1);

        User user2 = new User();
        user2.setUserName("customer2");
        user2.setEmail("customer2@gmail.com");
        user2.setPassword("123");
        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUserName("admin");
        user3.setEmail("admin1@gmail.com");
        user3.setPassword("password");
        userService.saveOrUpdate(user3);
    }

    private void assignRolesToUsers(){
        List<User> users = (List<User>)userService.listAll();
        List<Role> roles = (List<Role>)roleService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equals("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            } else if (role.getRole().equals("ADMIN")){
                users.forEach(user -> {
                    if (user.getUserName().equals("admin")){
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void generateProductsAndBundles() {
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
        popluarMulti.setImageUrl("http://i.imgur.com/aitnt7i.png");

        Bundle rpgs = new Bundle();
        rpgs.setName("Role Playing Games");
        rpgs.setImageUrl("http://www.wallpapersxl.com/get/2532890");


        Developer nullDev = new Developer();
        nullDev.setName("Default Developer");
        nullDev.setId(1);
        developerService.saveOrUpdate(nullDev);

        Publisher nullPub = new Publisher();
        nullPub.setName("Default Publisher");
        nullPub.setId(1);
        publisherService.saveOrUpdate(nullPub);


        productService.saveOrUpdate(productGenerator("Distant Worlds","Space 4X",new BigDecimal(10),"http://www.codeforce.co.nz/favicon.ico","https://www.youtube.com/embed/eUtjeVL58yY",codeForce,matrixGames));
        productService.saveOrUpdate(productGenerator("Distant Worlds Universe","Space 4X",new BigDecimal(50), "http://www.etcwiki.org/images/thumb/d/da/Distant_worlds_universe_large_box.jpg/300px-Distant_worlds_universe_large_box.jpg","https://www.youtube.com/embed/eUtjeVL58yY",codeForce,matrixGames));
        bundleBinding(popluarMulti, productGenerator("Counter-Strike","FPS",new BigDecimal(10), "http://orig04.deviantart.net/b9d0/f/2015/179/e/2/counter_strike_global_offensive_icon_by_ru_devlin-d8z2vpe.png","https://www.youtube.com/embed/edYCtaNueQY",valveDev,valvePub));
        bundleBinding(popluarMulti, productGenerator("Dota 2","RTS",new BigDecimal(0), "http://orig06.deviantart.net/f3d1/f/2012/192/0/9/dota_2_icon_by_snaapsnaap-d56t8wg.png","https://www.youtube.com/embed/-cSFPIwMEq4",valveDev,valvePub));

        Product newProduct = productGenerator("Mount & Blade","ARPG",new BigDecimal(10), "http://www.iconarchive.com/download/i539/3xhumed/mega-games-pack-25/Mount-Blade-1.ico","https://www.youtube.com/embed/sX37QA9o-Co",taleWorlds,paradoxPub);
        bundleBinding(rpgs, newProduct);
        bundleBinding(popluarMulti, newProduct);

        productService.saveOrUpdate(productGenerator("Europa Universalis IV","strategy",new BigDecimal(25), "http://orig02.deviantart.net/0725/f/2012/232/1/1/europa_universalis_4_by_nerces-d5bt8m1.png","https://www.youtube.com/embed/ONWTGEZKzn8",paradoxDev,paradoxPub));
        productService.saveOrUpdate(productGenerator("Stellaris","Space 4X",new BigDecimal(40), "http://orig11.deviantart.net/abe4/f/2016/133/3/f/icon_stellaris_by_hazzbrogaming-da2b15o.png","https://www.youtube.com/embed/KanCiSGxSKM",paradoxDev,paradoxPub));
        bundleBinding(rpgs, productGenerator("Pillars of Eternity","RPG",new BigDecimal(30), "http://orig12.deviantart.net/f509/f/2015/082/2/6/pillars_of_eternity___icon_by_blagoicons-d8mswa6.png","https://www.youtube.com/embed/uclz-RhyMpo",obsidian,paradoxPub));
        bundleBinding(rpgs, productGenerator("Fallout: New Vegas","ARPG",new BigDecimal(20), "http://orig05.deviantart.net/39e2/f/2011/045/7/3/fallout_new_vegas_by_madrapper-d39imeq.png","https://www.youtube.com/embed/cIzOttk6Dv4",obsidian,bethesdaPub));
        bundleBinding(rpgs, productGenerator("The Elder Scrolls V: Skyrim","ARPG",new BigDecimal(30), "http://img06.deviantart.net/5f8f/i/2011/046/4/3/elder_scrolls_v___skyrim_icon_by_bonscha-d39n68b.png","https://www.youtube.com/embed/PjqsYzBrP-M",bethesdaDev,bethesdaPub));

        bundleService.saveOrUpdate(popluarMulti);
        bundleService.saveOrUpdate(rpgs);
    }

    private Product productGenerator(String name, String description, BigDecimal price, String imageUrl,String youtubeUrl, Developer developer, Publisher publisher){
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setImageUrl(imageUrl);
        newProduct.setYoutubeUrl(youtubeUrl);

        developer.addProduct(newProduct);

        publisher.addProduct(newProduct);

        newProduct.setDeveloper(developer);
        newProduct.setPublisher(publisher);

        return newProduct;
    }

    private void bundleBinding(Bundle bundle, Product product){

        bundle.addProduct(product);
        product.addBundle(bundle);
    }

    public boolean getInitaitedStatus(){
        return initiated;
    }
}
