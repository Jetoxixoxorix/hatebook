package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.services.FriendManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FriendController {

    private FriendManager friendManager;
    private IUserManager userManager;

    public FriendController(FriendManager friendManager, IUserManager userManager){
        this.friendManager = friendManager;
        this.userManager = userManager;
    }

    @GetMapping("/friends")
    public String getFriendsList(Model model){
        model.addAttribute("friends", friendManager.getFriendsList(userManager.getUserId()));
        return "friends";
    }

    @PostMapping("/addfriend/{id}")
    public String addFriend(@PathVariable Long id, Model model){
        friendManager.addFriend(userManager.getUserId(), userManager.getById(id));
        return getFriendsList(model);
    }
}
