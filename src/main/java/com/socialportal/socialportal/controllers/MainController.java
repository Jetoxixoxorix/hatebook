package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    UserManager userManager;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model){
        userManager.register(user);
        return "registration";
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> allUsers(){
        return userManager.allUsers();
    }
}
