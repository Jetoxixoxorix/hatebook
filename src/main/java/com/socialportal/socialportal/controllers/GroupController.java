package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.services.ICollectiveManager;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IGroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {

    private ICollectiveManager collectiveManager;
    private IUserManager userManager;
    private IGroupValidator groupValidator;

    @Autowired
    public GroupController(ICollectiveManager collectiveManager, IUserManager userManager, IGroupValidator groupValidator) {
        this.collectiveManager = collectiveManager;
        this.userManager = userManager;
        this.groupValidator = groupValidator;
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
        model.addAttribute("admin", collectiveManager.isAdmin(collectiveManager.getGroup(groupId), userManager.getUserById(userManager.getUserId())));
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

    @GetMapping("/group/{id}/info")
    public String getGroupInfo(@PathVariable("id") Long groupId, Model model) {
        model.addAttribute("groupInfo", collectiveManager.getGroup(groupId));
        return "groupInfo";
    }

    @PostMapping("/group/{id}/info")
    public String changeGroupInfo(@PathVariable("id") Long groupId, String name, String description, Model model) {
        collectiveManager.changeGroupInfo(groupId, name, description);
        return getGroup(groupId, model);
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

        String error = groupValidator.isAdminAndIsMember(groupId, userId, model);

        if (error != null)
            return error;

        collectiveManager.removeFromGroup(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }

    @PostMapping("/makeadmin/{groupid}/{userid}")
    public String makeUserAnAdmin(@PathVariable("groupid") Long groupId, @PathVariable("userid") Long userId, Model model) {

        String error = groupValidator.isAdminAndIsMember(groupId, userId, model);

        if (error != null)
            return error;

        collectiveManager.makeUserAnAdmin(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }

    @PostMapping("/removeadmin/{groupid}/{userid}")
    public String removeAdminFromUser(@PathVariable("groupid") Long groupId, @PathVariable("userid") Long userId, Model model) {

        String error = groupValidator.isAdminAndIsMember(groupId, userId, model);

        if (error != null)
            return error;

        collectiveManager.removeAdminFromUser(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroupMembers(groupId, model);
    }
}
