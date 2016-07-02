package com.tom.test.controllers;

import com.tom.test.domain.Bundle;
import com.tom.test.services.BundleService;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tom on 6/11/2016.
 */
@Controller
@RequestMapping("/bundle")
public class BundleController {

    @Autowired
    private BundleService bundleService;
    @Autowired
    private ProductService productService;

    @ModelAttribute("fullProductList")
    public List<?> populateProductList(){return productService.listAll();}

    @RequestMapping({"/list","/"})
    public String listAll(Model model){
        model.addAttribute("bundles",bundleService.listAll());
        return "bundle/list";
    }

    @RequestMapping("/new")
    public String newBundle(Model model){
        model.addAttribute("bundle",new Bundle());
        return "bundle/bundleform";
    }

    @RequestMapping("/show/{id}")
    public String showBundle(@PathVariable Integer id, Model model){
        model.addAttribute("bundle",bundleService.getById(id));
        return "bundle/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateBundle(Bundle domainObject){
        Bundle savedBundle = bundleService.saveOrUpdate(domainObject);
        return "redirect:/bundle/show/" + savedBundle.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteBundle(@PathVariable Integer id){
        bundleService.delete(id);
        return "redirect:/bundle/list";
    }
}
