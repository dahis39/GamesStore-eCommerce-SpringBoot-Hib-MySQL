package com.tom.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by tom on 9/1/2016.
 */
@Controller
@SessionAttributes({"userEmail"})
public class AboutController {

    @ModelAttribute("page")
    public String module() {
        return "about";
    }

    @RequestMapping("/about")
    public String doGet(){
        return "about";
    }
}
