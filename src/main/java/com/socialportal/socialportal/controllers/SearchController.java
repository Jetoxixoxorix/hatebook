package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IFriendManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SearchController {

    private IUserManager userManager;
    private IFriendManager friendManager;

    @Autowired
    public SearchController(IUserManager userManager, IFriendManager friendManager){
        this.userManager = userManager;
        this.friendManager = friendManager;
    }

    @GetMapping("/search")
    public String searchUsers(Model model){
        model.addAttribute("search", new User());
        return "search";
    }

    @PostMapping("/search")
    public String searchUsers(@ModelAttribute("search") User user, Model model) {
        model.addAttribute("users", userManager.findUsersByName(user.getFirstName()));
        model.addAttribute("loggedUserid", userManager.getUserId());
        model.addAttribute("friends", friendManager.getUsersFromFriendsList(userManager.getUserId()));
        return "searchResults";
    }

}
