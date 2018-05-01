package com.socialportal.socialportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroupController {

    @GetMapping("/groups")
    public String getGroups() {
        return "groups";
    }

    @GetMapping("/creategroup")
    public String createGroup() {
        return "createGroup";
    }
}
