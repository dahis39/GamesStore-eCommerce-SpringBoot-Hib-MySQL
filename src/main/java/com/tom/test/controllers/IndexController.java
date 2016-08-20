package com.tom.test.controllers;

import com.tom.test.domain.Bundle;
import com.tom.test.services.BundleService;
import com.tom.test.services.DummyDataGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tom on 7/6/2016.
 */
@Controller
public class IndexController {

    @Autowired
    BundleService bundleService;

    @ModelAttribute("page")
    public String module() {
        return "index";
    }

    @ModelAttribute("bundleList")
    public List<?> getBundleList(){
        return bundleService.listAll();
    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        return "access_denied";
    }

    @RequestMapping("login")
    public String loginForm(){
        return "login";
    }

    @RequestMapping({"/","index","home"})
    public String home(Model model){
        Bundle bundle = bundleService.getById(1);
        model.addAttribute("bundle", bundle);
        model.addAttribute("products",bundle.getProducts());
        return "index";
    }

    @RequestMapping("/index/{id}")
    public String showBundle(@PathVariable Integer id, Model model){
        Bundle bundle = bundleService.getById(id);
        model.addAttribute("bundle",bundle);
        model.addAttribute("products",bundle.getProducts());
        return "index";
    }
}
