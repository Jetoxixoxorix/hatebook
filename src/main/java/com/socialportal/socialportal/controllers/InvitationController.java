package com.socialportal.socialportal.controllers;


import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.InvitationManager;
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

    @Autowired
    public InvitationController(InvitationManager invitationManager, IUserManager userManager) {
        this.invitationManager = invitationManager;
        this.userManager = userManager;
    }

    @GetMapping("/invitations")
    public String getInvitations(Model model) {
        model.addAttribute("invitations", invitationManager.getInvitations(userManager.getUserId()));
        model.addAttribute("sendInvitations", invitationManager.getSendInvitations(userManager.getById(userManager.getUserId())));
        return "invitations";
    }

    //later change to post
    @GetMapping("/sendinvitation/{id}")
    public String sendInvitation(@PathVariable("id") Long id, Model model) {
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
