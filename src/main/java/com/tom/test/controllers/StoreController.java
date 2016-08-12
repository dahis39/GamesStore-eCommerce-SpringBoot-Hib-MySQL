package com.tom.test.controllers;

import com.tom.test.domain.Cart;
import com.tom.test.domain.CartDetail;
import com.tom.test.services.DummyDataGeneration;
import com.tom.test.services.ProductService;
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

    @ModelAttribute("page")
    public String module() {
        return "store";
    }

    @RequestMapping(value = "/store", params={"addToCart"}, method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("cart") final Cart cart, final HttpServletRequest req){
        final Integer productId = Integer.valueOf(req.getParameter("addToCart"));
        System.out.println(productId);
        CartDetail newCartDetail = new CartDetail();
        newCartDetail.setQuantity(1);
        newCartDetail.setProduct(productService.getById(productId));
        cart.addCartDetail(newCartDetail);
        System.out.println("Product added" + productId + cart.getCartDetails().size());
        return "redirect:/store";
    }


    @RequestMapping("/store")
    public String store(Model model){
        boolean dataStatus = dummyDataGeneration.getInitaitedStatus();
        model.addAttribute("initaited",dataStatus);
        model.addAttribute("products",productService.listAll());
        if (!model.containsAttribute("cart")){
            model.addAttribute("cart",new Cart());
        }
        return "store";
    }
}
