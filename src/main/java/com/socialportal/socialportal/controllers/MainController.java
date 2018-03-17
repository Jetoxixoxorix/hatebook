package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.IStatusManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping
public class MainController {

    private IUserManager userManager;
    private IStatusManager statusManager;

    @Autowired
    public MainController(IUserManager userManager, IStatusManager statusManager){
        this.userManager = userManager;
        this.statusManager = statusManager;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/userprofile/{id}")
    public String getUserProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("profile", userManager.getById(id));
        model.addAttribute("statuses", statusManager.getStatuses(id));
        return "userProfile";
    }

    @GetMapping("/status")
    public String addNewStatus(Model model){
        model.addAttribute("add", new UserStatus());
        return "status";
    }

    @PostMapping("/status")
    public String addNewStatus(@ModelAttribute("add") UserStatus userStatus){
        statusManager.addNewStatus(userStatus, userManager.getUserId());
        return "status";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new User());
        return "login";
    }

    //temporary
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> allUsers() {
        return userManager.allUsers();
    }

    //temporary
    @GetMapping("/statuses")
    public @ResponseBody Iterable<UserStatus> allStatuses(){
        return statusManager.allStatus();
    }
}
