package com.tom.test.controllers;

import com.tom.test.commands.UserForm;
import com.tom.test.domain.User;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by tom on 6/8/2016.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("users",userService.listAll());
        return "user/list";
    }

    @RequestMapping("/new")
    public String createUser(Model model){
        model.addAttribute("userForm",new UserForm());
        return "user/userform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateUser(@Valid UserForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return  "user/userform";
        }
        User savedUser = userService.saveOrUpdateUserForm(userForm);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/show/{id}")
    public String showUser(@PathVariable Integer id,Model model){
        model.addAttribute("user",userService.getById(id));
        return "user/show";
    }
}
