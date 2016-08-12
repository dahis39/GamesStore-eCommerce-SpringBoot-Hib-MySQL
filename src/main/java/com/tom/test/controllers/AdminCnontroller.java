package com.tom.test.controllers;

import com.tom.test.services.DeveloperService;
import com.tom.test.services.DummyDataGeneration;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tom on 8/1/2016.
 */
@Controller
public class AdminCnontroller {

    @Autowired
    DummyDataGeneration dummyDataGeneration;


    @ModelAttribute("page")
    public String module() {
        return "admin";
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        boolean dataStatus = dummyDataGeneration.getInitaitedStatus();
        model.addAttribute("initaited",dataStatus);
        return "admin";
    }

    @RequestMapping(value = "/generatedummydata", params = "comeFrom")
    public String generateDummyData(@RequestParam("comeFrom")String comeFrom){
        if (!dummyDataGeneration.getInitaitedStatus())
            dummyDataGeneration.generate();
        if (comeFrom.equals("admin")){
            return "redirect:/admin";
        }
        else if (comeFrom.equals("store")){
            return "redirect:/store";
        }
        return "redirect:/index";
    }
}
