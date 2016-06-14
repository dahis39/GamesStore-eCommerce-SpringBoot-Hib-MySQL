package com.tom.test.controllers;

import com.tom.test.domain.User;
import com.tom.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("user",new User());
        return "user/userform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateUser(User user){
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/show/{id}")
    public String showUser(@PathVariable Integer id,Model model){
        model.addAttribute("user",userService.getById(id));
        return "user/show";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id,Model model){
        model.addAttribute("user",userService.getById(id));
        return "user/userform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user/list";
    }
}
