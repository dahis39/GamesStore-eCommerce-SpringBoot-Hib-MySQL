package com.tom.test.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 6/8/2016.
 */
@Entity
public class Bundle extends CommonGoodDetails{

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRODUCT_ID")
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
