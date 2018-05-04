package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.services.CollectiveManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupController {

    private CollectiveManager collectiveManager;

    @Autowired
    public GroupController(CollectiveManager collectiveManager) {
        this.collectiveManager = collectiveManager;
    }

    @GetMapping("/groups")
    public String getGroups() {
        return "groups";
    }

    @GetMapping("/creategroup")
    public String createGroup(Model model) {
        model.addAttribute("createGroup", new Collective());
        return "createGroup";
    }

    @PostMapping("/creategroup")
    public String createGroup(@ModelAttribute("createGroup") Collective group, Model model){
        collectiveManager.createGroup(group);
        return "createGroup";
    }

}
