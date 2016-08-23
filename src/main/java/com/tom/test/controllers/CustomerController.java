package com.tom.test.controllers;

import com.tom.test.domain.User;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by tom on 8/23/2016.
 */
@Controller
public class CustomerController {

    @Autowired
    UserService userService;

    @RequestMapping("/customer/orderhistory")
    public String orderHistory(Model model, Principal principal){
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            model.addAttribute("user",user);
            return "customer/orderhistory";
        }
        return "access_denied";
    }
}
