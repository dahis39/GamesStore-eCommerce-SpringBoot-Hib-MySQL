package com.tom.test.controllers;

import com.tom.test.commands.DeveloperForm;
import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.services.DeveloperService;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tom on 6/14/2016.
 */
@Controller
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    DeveloperService developerService;
    @Autowired
    ProductService productService;

    @RequestMapping({"/","/list"})
    public String list(Model model){
        model.addAttribute("developers",developerService.listAll());
        return"developer/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id,Model model){
        model.addAttribute("developer",developerService.getById(id));
        return "developer/show";
    }

    @RequestMapping("/new")
    public String newDeveloper(Model model){
        model.addAttribute("developerForm",new DeveloperForm());
        return "developer/developerform";
    }

    @RequestMapping( params={"addProduct"})
    public String addProduct(final DeveloperForm developerForm){
        developerForm.getDeveloperProducts().add(new Product());
        return "developer/developerform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(DeveloperForm developerForm){
        Developer savedDeveloper = developerService.saveOrUpdateDeveloperForm(developerForm);
        return "redirect:/developer/show/" + savedDeveloper.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("developerForm",developerService.getDeveloperFormById(id));
        return "developer/developerform";
    }
}
