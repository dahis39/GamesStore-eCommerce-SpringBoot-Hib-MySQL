package com.tom.test.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/17/2016.
 */
@Entity
public class OrderHistory extends AbstartDomainClass {

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
