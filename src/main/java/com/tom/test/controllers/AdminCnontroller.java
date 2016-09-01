package com.tom.test.controllers;

import com.tom.test.services.DeveloperService;
import com.tom.test.services.DummyDataGeneration;
import com.tom.test.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tom on 8/1/2016.
 */
@Controller
@SessionAttributes({"userEmail"})
public class AdminCnontroller {

    @Autowired
    DummyDataGeneration dummyDataGeneration;


    @ModelAttribute("page")
    public String module() {
        return "admin";
    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        return "access_denied";
    }

    @RequestMapping("/workinprogress")
    public String workInProgressPages(){
        return "workinprogress";
    }

    @RequestMapping("login")
    public String loginForm(){
        return "login";
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

    @RequestMapping("/generatedummydata")
    public String directGenerate(){
        dummyDataGeneration.generate();
        return "redirect:/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/store";
    }
}
