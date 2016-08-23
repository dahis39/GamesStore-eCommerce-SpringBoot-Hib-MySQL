package com.tom.test.controllers;

import com.tom.test.domain.*;
import com.tom.test.services.RoleService;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Created by tom on 8/17/2016.
 */
@Controller
public class PurchaseCompleteController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/thankyou", method = RequestMethod.GET)
    public String singlePurchase(){
        return "thankyou";
    }

    @RequestMapping(value = "/thankyou", method = RequestMethod.POST)
    public String payAsGuest(User user, HttpSession session){
        User dbUser = userService.findByUserName(user.getEmail());
        if (dbUser != null){
            user = dbUser;
        }
        else {
            Role role = roleService.getById(3);
            user.addRole(role);
            user.setUserName(user.getEmail());
        }

        Cart cart = (Cart) session.getAttribute("cart");
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setCartDetails(cart.getCartDetails());
        orderHistory.setTotalPrice(totalPriceCalulator(orderHistory.getCartDetails()));
        user.addOrderHistory(orderHistory);
        orderHistory.setUser(user);

        userService.saveOrUpdate(user);
        session.removeAttribute("cart");
        return "thankyou";
    }

    @RequestMapping(value = "/thankyou", params = "payAsUser", method = RequestMethod.POST)
    public String payAsUser(HttpSession session, Principal principal){
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            Cart cart = (Cart) session.getAttribute("cart");

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setCartDetails(cart.getCartDetails());
            orderHistory.setTotalPrice(totalPriceCalulator(orderHistory.getCartDetails()));
            user.addOrderHistory(orderHistory);
            orderHistory.setUser(user);

            Cart newCart = new Cart();
            user.setCart(newCart);
            newCart.setUser(user);

            userService.saveOrUpdate(user);
            session.removeAttribute("cart");
        }
        return "thankyou";
    }

    private BigDecimal totalPriceCalulator(List<CartDetail> cartDetails){
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (CartDetail cartDetail : cartDetails){
            totalPrice = totalPrice.add(cartDetail.getProduct().getPrice());
        }
        return totalPrice;
    }
}
