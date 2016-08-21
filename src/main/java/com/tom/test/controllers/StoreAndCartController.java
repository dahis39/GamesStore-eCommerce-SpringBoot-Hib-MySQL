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
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 8/4/2016.
 */
@Controller
@SessionAttributes({"cart","userEmail"})
public class StoreAndCartController {

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

    @RequestMapping("/store")
    public String store(Model model, Principal principal, HttpSession session){
        boolean dataStatus = dummyDataGeneration.getInitaitedStatus();
        model.addAttribute("initaited",dataStatus);
        model.addAttribute("products",productService.listAll());

        if (!model.containsAttribute("cart")) {
            if (principal != null){
                User user = userService.findByUserName(principal.getName());
                if ( user.getCart()!= null) {
                    model.addAttribute("cart", user.getCart());
                    return "store";
                }
            }
            model.addAttribute("cart", new Cart());
        }
        setupUserEmail(model,principal,session);
        return "store";
    }

    @RequestMapping(value = "/store", params={"addToCart"}, method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("cart") final Cart cart, final HttpServletRequest req, Principal principal){
        final Integer productId = Integer.valueOf(req.getParameter("addToCart"));
        CartDetail newCartDetail = new CartDetail();
        newCartDetail.setQuantity(1);
        newCartDetail.setProduct(productService.getById(productId));
        cart.addCartDetail(newCartDetail);
        saveOrUpdateCartToUser(principal,cart);
        return "redirect:/store";
    }

    @RequestMapping("/cart")
    public String cart(Model model, Principal principal,HttpSession session){
        if (model.containsAttribute("cart")){
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart.getCartDetails()!= null){
                model.addAttribute("totalPrice",totalPriceCalulator(cart.getCartDetails()));
                setupUserEmail(model,principal,session);
                return "cart";
            }
        }
        model.addAttribute("totalPrice",0.00);
        setupUserEmail(model,principal,session);
        return "cart";
    }

    @RequestMapping(value = "/cart", params={"removeCartDetail"}, method = RequestMethod.POST)
    public String removeCartDetail(Cart cart, HttpServletRequest req, Principal principal){
        final Integer cartDetailId = Integer.valueOf(req.getParameter("removeCartDetail"));
        System.out.println(cartDetailId);
        List<CartDetail> tempCartDetails = cart.getCartDetails();
        tempCartDetails.remove(cartDetailId.intValue());
        cart.setCartDetails(tempCartDetails);
        saveOrUpdateCartToUser(principal,cart);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String submitCart(Cart cart,Principal principal, Model model){
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            user.setCart(cart);
            cart.setUser(user);
            model.addAttribute("user",user);
            model.addAttribute("totalPrice",totalPriceCalulator(cart.getCartDetails()));
            return "checkout";
        }
        else {
            User user = new User();
            user.setCart(cart);
            cart.setUser(user);
            model.addAttribute("user",user);
            model.addAttribute("totalPrice",totalPriceCalulator(cart.getCartDetails()));
            return "checkout";
        }
    }

    private BigDecimal totalPriceCalulator(List<CartDetail> cartDetails){
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (CartDetail cartDetail : cartDetails){
            totalPrice = totalPrice.add(cartDetail.getProduct().getPrice());
        }
        return totalPrice;
    }
    private void saveOrUpdateCartToUser(Principal principal,Cart cart){
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            user.setCart(cart);
            cart.setUser(user);
            userService.saveOrUpdate(user);
        }
    }
    private Model setupUserEmail(Model model, Principal principal, HttpSession session){
        if (principal != null){
            if (session.getAttribute("userEmail") == null){
                User user = userService.findByUserName(principal.getName());
                model.addAttribute("userEmail",user.getEmail());
                return model;
            }
        }
        return model;
    }
}
