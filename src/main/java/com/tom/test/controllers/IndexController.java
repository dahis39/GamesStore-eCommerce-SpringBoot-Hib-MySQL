package com.tom.test.controllers;

import com.tom.test.domain.Bundle;
import com.tom.test.domain.User;
import com.tom.test.services.BundleService;
import com.tom.test.services.DummyDataGeneration;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * Created by tom on 7/6/2016.
 */
@Controller
@SessionAttributes ({"userEmail"})
public class IndexController {

    @Autowired
    BundleService bundleService;
    @Autowired
    UserService userService;

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

    @RequestMapping("/workinprogress")
    public String workInProgressPages(){
        return "workinprogress";
    }

    @RequestMapping("login")
    public String loginForm(){
        return "login";
    }


    @RequestMapping({"/","index","home"})
    public String home(Model model, Principal principal, HttpSession session){
        Bundle bundle = bundleService.getById(1);
        model.addAttribute("bundle", bundle);
        if (bundle != null) {
            model.addAttribute("products", bundle.getProducts());
        }
        setupUserEmail(model, principal, session);
        return "index";
    }

    @RequestMapping("/{id}")
    public String showBundle(@PathVariable Integer id, Model model, Principal principal, HttpSession session){
        Bundle bundle = bundleService.getById(id);
        model.addAttribute("bundle",bundle);
        if (bundle != null) {
            model.addAttribute("products", bundle.getProducts());
        }
        setupUserEmail(model,principal,session);
        return "index";
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
