package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.HasThisFriendException;
import com.socialportal.socialportal.errors.SameUserException;
import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.services.IFriendManager;
import com.socialportal.socialportal.services.IInvitationManager;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
    private IInvitationManager invitationManager;

    @Autowired
    public FriendController(IFriendManager friendManager, IUserManager userManager, IUserValidator userValidator, IInvitationManager iInvitationManager) {
        this.friendManager = friendManager;
        this.userManager = userManager;
        this.userValidator = userValidator;
        this.invitationManager = iInvitationManager;
    }

    @GetMapping("/friends/{id}")
    public String getFriendsList(Model model, @PathVariable("id") Long userProfileid) {
        model.addAttribute("friends", friendManager.getFriendsOfUser(userProfileid));
        model.addAttribute("yourFriends", friendManager.getUsersFromFriendsOfUser(userManager.getUserId()));
        model.addAttribute("loggedUserid", userManager.getUserId());
        return "friends";
    }

    @PostMapping("/addfriend/{addedFriendId}/{invitationid}")
    public String addFriend(@PathVariable Long addedFriendId, @PathVariable("invitationid") Long invitationId, Model model) {
        try {
            userValidator.checkAddingFriend(userManager.getUserId(), addedFriendId);
        } catch (SameUserException e) {
            model.addAttribute("sameUser", e.getMessage());
            return "errors";
        } catch (HasThisFriendException e) {
            model.addAttribute("haveThisFriend", e.getMessage());
            return "errors";
        }

        friendManager.addFriend(userManager.getUserId(), userManager.getUserById(addedFriendId), invitationId);
        return getFriendsList(model, userManager.getUserId());
    }

    @PostMapping("/addfriend/{userId}")
    public String addFriend(@PathVariable("userId") Long id, Model model) {
        Invitation invitation = invitationManager.getInvitation(userManager.getUserById(id), userManager.getUserById(userManager.getUserId()));
        return addFriend(id, invitation.getId(), model);
    }

    @PostMapping("/deletefriend/{id}")
    public String deleteFriend(@PathVariable("id") Long id, Model model) {
        friendManager.deleteFriend(userManager.getUserId(), userManager.getUserById(id));
        return getFriendsList(model, userManager.getUserId());
    }
}
