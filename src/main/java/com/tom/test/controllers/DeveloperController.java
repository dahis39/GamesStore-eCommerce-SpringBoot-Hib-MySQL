package com.tom.test.controllers;

import com.tom.test.commands.DeveloperForm;
import com.tom.test.converters.DeveloperFormToDeveloper;
import com.tom.test.domain.AbstartDomainClass;
import com.tom.test.domain.Developer;
import com.tom.test.domain.Product;
import com.tom.test.domain.Publisher;
import com.tom.test.services.DeveloperService;
import com.tom.test.services.ProductService;
import com.tom.test.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    PublisherService publisherService;

    @Autowired
    DeveloperFormToDeveloper developerFormToDeveloper;

    @ModelAttribute("publishers")
    public List<Publisher> populatePublisherList(){return (List<Publisher>) publisherService.listAll();}

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

    @RequestMapping(params = {"removeProduct"})
    public String removeProduct(final DeveloperForm developerForm, final HttpServletRequest req){
        final Integer productId = Integer.valueOf(req.getParameter("removeProduct"));
        developerForm.getDeveloperProducts().remove(productId.intValue());
        return "developer/developerform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(final DeveloperForm developerForm){
        Developer savedDeveloper = developerService.saveOrUpdateDeveloperForm(developerForm);
        return "redirect:/developer/show/" + savedDeveloper.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("developerForm",developerService.getDeveloperFormById(id));
        return "developer/developerform";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        developerService.delete(id);
        return "redirect:/developer/list";
    }
}
