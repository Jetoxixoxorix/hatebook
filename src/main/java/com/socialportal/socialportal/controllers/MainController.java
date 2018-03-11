package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.DifferentPasswordException;
import com.socialportal.socialportal.errors.ExistingEmailException;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.validators.IUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    IUserManager userManager;

    @Autowired
    IUserValidator userValidator;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userData", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userData") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "registration";

        try {
            userValidator.validateUser(user);
        } catch (ExistingEmailException e) {
            model.addAttribute("existingEmail", e.getMessage());
            return "registration";
        } catch (DifferentPasswordException e) {
            model.addAttribute("differentPasswords", e.getMessage());
            return "registration";
        }

        userManager.register(user);
        return "registrationCompleted";
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
}
