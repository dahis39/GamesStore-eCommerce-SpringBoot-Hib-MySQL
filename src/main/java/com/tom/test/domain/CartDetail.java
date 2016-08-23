package com.tom.test.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by tom on 8/3/2016.
 */
@Entity
public class CartDetail extends AbstartDomainClass {

    @ManyToOne
    private Product product;

    private Integer quantity;

//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
    public CartDetail (Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }
    public CartDetail (){
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
