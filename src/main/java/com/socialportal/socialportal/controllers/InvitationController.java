package com.socialportal.socialportal.controllers;


import com.socialportal.socialportal.errors.HasInvitationException;
import com.socialportal.socialportal.errors.HasThisFriendException;
import com.socialportal.socialportal.errors.SameUserException;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.InvitationManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class InvitationController {

    private InvitationManager invitationManager;
    private IUserManager userManager;
    private IUserValidator userValidator;

    @Autowired
    public InvitationController(InvitationManager invitationManager, IUserManager userManager, IUserValidator userValidator) {
        this.invitationManager = invitationManager;
        this.userManager = userManager;
        this.userValidator = userValidator;
    }

    @GetMapping("/invitations")
    public String getInvitations(Model model) {
        model.addAttribute("invitations", invitationManager.getReceivedInvitations(userManager.getUserId()));
        model.addAttribute("sendInvitations", invitationManager.getSendInvitations(userManager.getById(userManager.getUserId())));
        return "invitations";
    }

    //later change to post
    @GetMapping("/sendinvitation/{id}")
    public String sendInvitation(@PathVariable("id") Long id, Model model) {
        try{
            userValidator.checkSendInvitation(userManager.getUserId(), id);
        } catch (SameUserException e) {
            model.addAttribute("sameUser",e.getMessage());
            return "errors";
        } catch (HasThisFriendException e) {
            model.addAttribute("haveThisFriend", e.getMessage());
            return "errors";
        } catch (HasInvitationException e) {
            model.addAttribute("invitationExists", e.getMessage());
            return "errors";
        }


        invitationManager.sendInvitation(id, userManager.getUserId());
        return getInvitations(model);
    }

    //later change to post
    @GetMapping("/deleteinvitation/{id}")
    public String deleteInvitation(@PathVariable("id") Long id, Model model) {
        invitationManager.deleteInvitation(id);
        return getInvitations(model);
    }
}
