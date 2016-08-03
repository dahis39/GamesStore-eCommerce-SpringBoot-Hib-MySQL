package com.tom.test.controllers;

import com.tom.test.commands.ProductForm;
import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
import com.tom.test.services.DeveloperService;
import com.tom.test.services.ProductService;
import com.tom.test.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tom on 6/9/2016.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private PublisherService publisherService;

    @RequestMapping({"/list","/"})
    public String listAll(Model model){
        model.addAttribute("products",productService.listAll());
        return "product/list";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("productForm",new ProductForm());
        model.addAttribute("developers",developerService.listAll());
        model.addAttribute("publishers",publisherService.listAll());
        return "product/productform";
    }

    @RequestMapping("/edit/{id}")
    public String saveOrUpdateProduct(@PathVariable Integer id, Model model){
        model.addAttribute("productForm",productService.findProductFormById(id));
        model.addAttribute("developers",developerService.listAll());
        model.addAttribute("publishers",publisherService.listAll());
        return "product/productform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveProduct(ProductForm domainObject){
        domainObject.setProductDeveloper(developerService.getById(domainObject.getProductDeveloper().getId()));
        domainObject.setProductPublisher(publisherService.getById(domainObject.getProductPublisher().getId()));
        Product savedProduct = productService.saveOrUpdateProductForm(domainObject);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/show/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product",productService.getById(id));
        return "product/show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/product/list";
    }
}
