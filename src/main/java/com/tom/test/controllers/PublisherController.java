package com.tom.test.controllers;

import com.tom.test.domain.Publisher;
import com.tom.test.services.PublisherService;
import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tom on 6/27/2016.
 */
@Controller
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @RequestMapping({"/","/list"})
    public String listAll(Model model){
        model.addAttribute("publishers",publisherService.listAll());
        return "publisher/list";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Integer id,Model model){
        model.addAttribute("publisher",publisherService.getById(id));
        return "publisher/show";
    }

    @RequestMapping("/new")
    public String newPublisher(Model model){
        model.addAttribute("publisher",new Publisher());
        return "publisher/publisherform";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(Publisher domainObject){
        Publisher savedPublisher = publisherService.saveOrUpdate(domainObject);
        return "redirect:/publisher/show/" + savedPublisher.getId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("publisher",publisherService.getById(id));
        return "publisher/publisherform";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        publisherService.delete(id);
        return "redirect:/publisher/list";
    }
}
