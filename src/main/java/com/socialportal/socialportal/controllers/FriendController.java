package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.HasThisFriendException;
import com.socialportal.socialportal.errors.SameUserException;
import com.socialportal.socialportal.services.IFriendManager;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class FriendController {

    private IFriendManager friendManager;
    private IUserManager userManager;
    private IUserValidator userValidator;

    public FriendController(IFriendManager friendManager, IUserManager userManager, IUserValidator userValidator){
        this.friendManager = friendManager;
        this.userManager = userManager;
        this.userValidator = userValidator;
    }

    @GetMapping("/friends/{id}")
    public String getFriendsList(Model model, @PathVariable("id") Long userProfileid){
        model.addAttribute("friends", friendManager.getFriendsList(userProfileid));
        return "friends";
    }

    @PostMapping("/addfriend/{id}")
    public String addFriend(@PathVariable Long id, Model model){
        try{
            userValidator.checkAddingFriend(userManager.getUserId(), id);
        }catch (SameUserException e){
            model.addAttribute("sameUser", e.getMessage());
            return "errors";
        } catch (HasThisFriendException e) {
            model.addAttribute("haveThisFriend", e.getMessage());
            return "errors";
        }

        friendManager.addFriend(userManager.getUserId(), userManager.getById(id));
        friendManager.addFriend(id, userManager.getById(userManager.getUserId()));
        return getFriendsList(model, userManager.getUserId());
    }
}
