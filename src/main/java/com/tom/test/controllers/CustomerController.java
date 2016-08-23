package com.tom.test.controllers;

import com.tom.test.commands.UserForm;
import com.tom.test.domain.User;
import com.tom.test.services.RoleService;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by tom on 8/23/2016.
 */
@Controller
@SessionAttributes({"userEmail"})
public class CustomerController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping("/customer/orderhistory")
    public String orderHistory(Model model, Principal principal){
        if (principal != null){
            User user = userService.findByUserName(principal.getName());
            model.addAttribute("user",user);
            return "customer/orderhistory";
        }
        return "access_denied";
    }

    @RequestMapping("/customer/new")
    public String createUser(Model model){
        model.addAttribute("userForm",new UserForm());
        return "customer/customerform";
    }

    @RequestMapping(value = "/customer/post",method = RequestMethod.POST)
    public String saveOrUpdateCustomer(@Valid UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "customer/customerform";
        }
        userForm.addRole(roleService.getById(1));
        userService.saveOrUpdateUserForm(userForm);
        return "redirect:/login";
    }

    @RequestMapping("/customer/setting")
    public String customerSetting(Model model, Principal principal){
        if (principal != null){
            UserForm userForm = userService.findUserFormById(userService.findByUserName(principal.getName()).getId());
            model.addAttribute("userForm", userForm);
            return "customer/customerform";
        }
        return "access_denied";
    }
}
