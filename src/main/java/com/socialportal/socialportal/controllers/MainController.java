package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.HasPrivilegeException;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.IStatusManager;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class MainController {

    private IUserManager userManager;
    private IStatusManager statusManager;
    private IUserValidator userValidator;

    @Autowired
    public MainController(IUserManager userManager, IStatusManager statusManager, IUserValidator userValidator) {
        this.userManager = userManager;
        this.statusManager = statusManager;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/userprofile")
    public String getYourProfile(Model model) {
        return getUserProfile(userManager.getUserId(), model);
    }

    @GetMapping("/userprofile/{id}")
    public String getUserProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("add", new UserStatus());
        model.addAttribute("statuses", statusManager.getStatuses(id));
        model.addAttribute("loggedUserId", userManager.getUserId());
        model.addAttribute("userProfile", userManager.getById(id));
        if (userManager.getById(id) == null) {
            model.addAttribute("nonExistingUser", "There is no such user.");
        }
        return "userProfile";
    }

    @PostMapping("/userprofile/{id}")
    public String getUserProfile(@ModelAttribute("add") UserStatus userStatus, @PathVariable("id") Long id, Model model) {
        statusManager.addNewStatus(userStatus, id, userManager.getById(userManager.getUserId()));
        return getUserProfile(id, model);
    }

    @GetMapping("/status")
    public String addNewStatus(Model model) {
        model.addAttribute("add", new UserStatus());
        return "status";
    }

    @PostMapping("/status")
    public String addNewStatus(@ModelAttribute("add") UserStatus userStatus) {
        statusManager.addNewStatus(userStatus, userManager.getUserId(), userManager.getById(userManager.getUserId()));
        return "status";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new User());
        return "login";
    }

    @GetMapping("/userprofile/{userid}/delete/{id}")
    public String deleteStatus(Model model, @PathVariable("id") Long id, @PathVariable("userid") Long userId) {
        try {
            userValidator.checkPrivilege(userManager.getUserId(), statusManager.getAuthorOfStatus(id), userId);
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "index";
        }

        statusManager.deleteStatus(id);
        return getUserProfile(userId, model);
    }

    @GetMapping("/userprofile/{userid}/edit/{id}")
    public String editStatus(Model model, @PathVariable("id") Long id, @PathVariable("userid") Long userId) {
        try {
            userValidator.editPrivilege(userManager.getUserId(), statusManager.getAuthorOfStatus(id));
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "index";
        }

        model.addAttribute("userStatus", statusManager.getUserStatus(id));
        return "edit";
    }

    @PostMapping("/userprofile/{userid}/edit/{id}")
    public String editStatus(Model model, @PathVariable("id") Long id, @PathVariable("userid") Long userId, String content) {
        statusManager.editUserStatus(statusManager.getUserStatus(id), id, content);
        return getUserProfile(userId, model);
    }

    //temporary
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> allUsers() {
        return userManager.allUsers();
    }

    //temporary
    @GetMapping("/statuses")
    public @ResponseBody
    Iterable<UserStatus> allStatuses() {
        return statusManager.allStatus();
    }
}
