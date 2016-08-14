package com.tom.test.controllers;

import com.tom.test.domain.Cart;
import com.tom.test.domain.CartDetail;
import com.tom.test.domain.User;
import com.tom.test.services.DummyDataGeneration;
import com.tom.test.services.ProductService;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by tom on 8/4/2016.
 */
@Controller
@SessionAttributes({"cart"})
public class StoreController {

    @Autowired
    ProductService productService;
    @Autowired
    DummyDataGeneration dummyDataGeneration;
    @Autowired
    UserService userService;

    @ModelAttribute("page")
    public String module() {
        return "store";
    }

    @RequestMapping(value = "/store", params={"addToCart"}, method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("cart") final Cart cart, final HttpServletRequest req, Principal principal){
        final Integer productId = Integer.valueOf(req.getParameter("addToCart"));
        CartDetail newCartDetail = new CartDetail();
        newCartDetail.setQuantity(1);
        newCartDetail.setProduct(productService.getById(productId));
        cart.addCartDetail(newCartDetail);
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            System.out.println(user.getUserName());
            user.setCart(cart);
            cart.setUser(user);
            userService.saveOrUpdate(user);
            System.out.println(cart.getUser().getUserName() + " " + newCartDetail.getProduct().getName());
        }
        return "redirect:/store";
    }


    @RequestMapping("/store")
    public String store(Model model, Principal principal){
        boolean dataStatus = dummyDataGeneration.getInitaitedStatus();
        model.addAttribute("initaited",dataStatus);
        model.addAttribute("products",productService.listAll());
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            if (!model.containsAttribute("cart") && user.getCart()!= null) {
                model.addAttribute("cart", user.getCart());
                return "store";
            }
        }
        if (!model.containsAttribute("cart")) {
            model.addAttribute("cart", new Cart());
        }
        return "store";
    }
}
