package com.tom.test.controllers;

import com.tom.test.commands.BundleForm;
import com.tom.test.converters.BundleFormToBundle;
import com.tom.test.converters.BundleToBundleForm;
import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.Bundle;
import com.tom.test.domain.Product;
import com.tom.test.services.BundleService;
import com.tom.test.services.ProductService;
import com.tom.test.services.sortList.ListSortingService;
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
    @Autowired
    private ListSortingService listSortingService;

    @Autowired
    private BundleFormToBundle bundleFormToBundle;
    @Autowired
    private BundleToBundleForm bundleToBundleForm;

    @ModelAttribute("sortedByDateProductList")
    public List<?> populateProductList(){
        List<AbstartDomainClass> productList = (List<AbstartDomainClass>) productService.listAll();
        return  listSortingService.SortListByDateCreated(productList);
    }

    @RequestMapping({"/list","/"})
    public String listAll(Model model){
        model.addAttribute("bundles",bundleService.listAll());
        return "bundle/list";
    }

    @RequestMapping("/new")
    public String newBundle(Model model){
        model.addAttribute("bundleForm",new BundleForm());
        return "bundle/bundleform";
    }

    @RequestMapping("/show/{id}")
    public String showBundle(@PathVariable Integer id, Model model){
        model.addAttribute("bundle",bundleService.getById(id));
        return "bundle/show";
    }

    @RequestMapping("/edit/{id}")
    public String editBundleForm(@PathVariable Integer id, Model model){
        model.addAttribute("bundleForm", bundleToBundleForm.convert(bundleService.getById(id)));
        return "bundle/bundleform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateBundle(BundleForm domainObject){
        System.out.println(domainObject.getBundlePruductIds());
        Bundle savedBundle = bundleService.saveOrUpdate(bundleFormToBundle.convert(domainObject));
        return "redirect:/bundle/show/" + savedBundle.getId();
    }

    @RequestMapping("/delete/{id}")
    public String deleteBundle(@PathVariable Integer id){
        bundleService.delete(id);
        return "redirect:/bundle/list";
    }
}
