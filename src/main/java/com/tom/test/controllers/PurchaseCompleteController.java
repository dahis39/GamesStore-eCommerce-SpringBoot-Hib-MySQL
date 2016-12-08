package com.tom.test.controllers;

import com.tom.test.converters.BundleToOderHistory;
import com.tom.test.domain.*;
import com.tom.test.services.BundleService;
import com.tom.test.services.RoleService;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/17/2016.
 */
@Controller
@SessionAttributes({"userEmail"})
public class PurchaseCompleteController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    BundleService bundleService;
    @Autowired
    BundleToOderHistory bundleToOderHistory;

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
        orderHistory.setOrderType("Store Cart Purchase");
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
            Cart sessionCart = (Cart) session.getAttribute("cart");

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setCartDetails(sessionCart.getCartDetails());
            orderHistory.setTotalPrice(totalPriceCalulator(orderHistory.getCartDetails()));
            orderHistory.setOrderType("Store Cart Purchase");
            user.addOrderHistory(orderHistory);
            orderHistory.setUser(user);

            if (user.getCart() == null){
                user.setCart(new Cart());
            }
            Cart dbCart = user.getCart();
            dbCart.setCartDetails(new ArrayList<>());
            user.setCart(dbCart);
            dbCart.setUser(user);

            userService.saveOrUpdate(user);
            session.removeAttribute("cart");
        }
        return "thankyou";
    }

    @RequestMapping(value = "/thankyou", params = "bundleCheckout", method = RequestMethod.POST)
    public String bundleCheckout(Bundle bundle, Principal principal){
        if (principal != null){
            Bundle dbBundle = bundleService.getById(bundle.getId());

            User user = userService.findByUserName(principal.getName());
            OrderHistory orderHistory = bundleToOderHistory.convert(dbBundle);
            orderHistory.setOrderType("Bundle Purchase");

            user.addOrderHistory(orderHistory);
            orderHistory.setUser(user);

            userService.saveOrUpdate(user);
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
