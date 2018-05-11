package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.services.CollectiveManager;
import com.socialportal.socialportal.services.UserManager;
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

    @Autowired
    public GroupController(CollectiveManager collectiveManager, UserManager userManager) {
        this.collectiveManager = collectiveManager;
        this.userManager = userManager;
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
        collectiveManager.removeFromGroup(collectiveManager.getGroup(groupId), userManager.getUserById(userId));
        return getGroup(groupId, model);
    }
}
