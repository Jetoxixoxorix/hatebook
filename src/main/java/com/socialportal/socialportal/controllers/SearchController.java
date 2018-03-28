package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SearchController {

    private IUserManager userManager;

    @Autowired
    public SearchController(IUserManager userManager){
        this.userManager = userManager;
    }

    @GetMapping("/search")
    public String searchUsers(Model model){
        model.addAttribute("search", new User());
        return "search";
    }

    @PostMapping("/search")
    public String searchUsers(@ModelAttribute("search") User user, Model model) {
        model.addAttribute("users", userManager.findUsersByName(user.getFirstName()));
        return "searchResults";
    }

}
