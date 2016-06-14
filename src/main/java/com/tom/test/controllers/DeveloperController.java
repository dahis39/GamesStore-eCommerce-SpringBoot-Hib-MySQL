package com.tom.test.controllers;

import com.tom.test.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tom on 6/14/2016.
 */
@Controller
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    DeveloperService developerService;

    @RequestMapping("/{id}")
    public String list(@PathVariable Integer id, Model model){
        model.addAttribute("products",developerService.getById(id).getProducts());
        return"product/list";
    }
}
