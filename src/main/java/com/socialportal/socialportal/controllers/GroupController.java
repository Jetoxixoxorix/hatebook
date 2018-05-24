package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.NotAMemberOfGroup;
import com.socialportal.socialportal.errors.NotAnAdminException;
import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.services.CollectiveManager;
import com.socialportal.socialportal.services.UserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {

    private CollectiveManager collectiveManager;
    private UserManager userManager;
    private IUserValidator userValidator;

    @Autowired
    public GroupController(CollectiveManager collectiveManager, UserManager userManager, IUserValidator userValidator) {
        this.collectiveManager = collectiveManager;
        this.userManager = userManager;
        this.userValidator = userValidator;
    }

    @GetMapping("/groups")
    public String getGroups(Model model) {
        model.addAttribute("groups", collectiveManager.getGroups(userManager.getUserById(userManager.getUserId())));
        return "groups";
    }

    @GetMapping("/group/{id}")
    public String getGroup(@PathVariable("id") Long groupId, Model model) {
        model.addAttribute("group", collectiveManager.getGroup(groupId));
        model.addAttribute("member", collectiveManager.isMemberOfGroup(userManager.getUserById(userManager.getUserId()), collectiveManager.getGroup(groupId)));
        return "group";
    }

    @GetMapping("/group/{id}/members")
    public String getGroupMembers(@PathVariable("id") Long groupId, Model model) {
        model.addAttribute("group", collectiveManager.getGroup(groupId));
        model.addAttribute("member", collectiveManager.isMemberOfGroup(userManager.getUserById(userManager.getUserId()), collectiveManager.getGroup(groupId)));
        model.addAttribute("members", collectiveManager.getGroupMembers(collectiveManager.getGroup(groupId)));
        model.addAttribute("isAdmin", collectiveManager.isAdmin(collectiveManager.getGroup(groupId), userManager.getUserById(userManager.getUserId())));
        model.addAttribute("loggedUserId", userManager.getUserId());
        return "groupMembers";
    }

    @GetMapping("/creategroup")
    public String createGroup(Model model) {
        model.addAttribute("createGroup", new Collective());
        return "createGroup";
    }

    @PostMapping("/creategroup")
    public String createGroup(@ModelAttribute("createGroup") Collective group, Model model) {
        collectiveManager.createGroup(group, userManager.getUserById(userManager.getUserId()));
        return getGroups(model);
    }

    @PostMapping("/joingroup/{id}")
    public String joinGroup(@ModelAttribute("group") Collective group, @PathVariable("id") Long groupId, Model model) {
        collectiveManager.addMember(collectiveManager.getGroup(groupId), userManager.getUserById(userManager.getUserId()));
        return getGroup(groupId, model);
    }

    @PostMapping("/leavegroup/{id}")
    public String leaveGroup(@ModelAttribute("group") Collective group, @PathVariable("id") Long groupId, Model model) {
        collectiveManager.removeFromGroup(collectiveManager.getGroup(groupId), userManager.getUserById(userManager.getUserId()));
        return getGroups(model);
    }

    @PostMapping("/deleteuser/{groupid}/{userid}")
    public String removeFromGroup(@PathVariable("groupid") Long groupId, @PathVariable("userid") Long userId, Model model) {

        try {
            userValidator.isAMemberOfGroup(groupId, userId);
            userValidator.hasAdminPrivilige(groupId, userManager.getUserId());
        } catch (NotAnAdminException e) {
            model.addAttribute("notAnAdmin", e.getMessage());
            return "errors";
        } catch (NotAMemberOfGroup e) {
            model.addAttribute("notAMember", e.getMessage());
            return "errors";
        }

        collectiveManager.removeFromGroup(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }

    @PostMapping("/makeadmin/{groupid}/{userid}")
    public String makeUserAnAdmin(@PathVariable("groupid") Long groupId, @PathVariable("userid") Long userId, Model model) {
        collectiveManager.makeUserAnAdmin(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }

    @PostMapping("/removeadmin/{groupid}/{userid}")
    public String removeAdminFromUser(@PathVariable("groupid") Long groupId, @PathVariable("userid") Long userId, Model model) {
        collectiveManager.removeAdminFromUser(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }
}
