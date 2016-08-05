package com.tom.test.controllers;

import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tom on 8/4/2016.
 */
@Controller
public class StoreController {

    @Autowired
    ProductService productService;

    @ModelAttribute("page")
    public String module() {
        return "store";
    }

    @RequestMapping("/store")
    public String store(Model model){
        model.addAttribute("products",productService.listAll());
        return "store";
    }
}
