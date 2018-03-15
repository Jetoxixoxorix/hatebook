package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping
public class MainController {

    IUserManager userManager;
    IUserValidator userValidator;

    @Autowired
    public MainController(IUserManager userManager, IUserValidator userValidator){
        this.userManager = userManager;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/userprofile/{id}")
    public String getUserProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("profile", new User());
        return "userProfile/{id}";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new User());
        return "login";
    }

    //temporary
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> allUsers() {
        return userManager.allUsers();
    }
}
