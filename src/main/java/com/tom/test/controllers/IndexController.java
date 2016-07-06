package com.tom.test.controllers;

import com.tom.test.services.DummyDataGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tom on 7/6/2016.
 */
@Controller
public class IndexController {

    @Autowired
    DummyDataGeneration dummyDataGeneration;

    @RequestMapping({"/","index","home"})
    public String home(){
        return "index";
    }

    @RequestMapping("/generatedummydata")
    public String generateDummyData(){

        dummyDataGeneration.generateProduct();
        return "redirect:/product/list";
    }
}
