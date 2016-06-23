package com.tom.test.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Created by tom on 6/9/2016.
 */
@Entity
public class Developer extends AbstartDomainClass{

    private String name;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "developer")
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

}
