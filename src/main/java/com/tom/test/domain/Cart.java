package com.tom.test.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/3/2016.
 */
@Entity
public class Cart extends AbstartDomainClass{

        @OneToOne
        private User user;

        @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
        private List<CartDetail> cartDetails = new ArrayList<>();

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<CartDetail> getCartDetails() {
            return cartDetails;
        }

        public void setCartDetails(List<CartDetail> cartDetails) {
            this.cartDetails = cartDetails;
        }

        public void addCartDetail(CartDetail cartDetail){
            cartDetails.add(cartDetail);
//            cartDetail.setCart(this);
        }

        public void removeCartDetail(CartDetail cartDetail){
            this.cartDetails.remove(cartDetail);
//            cartDetail.setCart(null);
        }
}

