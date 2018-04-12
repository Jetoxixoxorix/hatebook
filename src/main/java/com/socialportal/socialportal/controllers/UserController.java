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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private IUserManager userManager;
    private IUserValidator userValidator;

    @Autowired
    public UserController(IUserManager userManager, IUserValidator userValidator){
        this.userManager = userManager;
        this.userValidator = userValidator;
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

        userManager.registerUser(user);
        model.addAttribute("registrationCompleted", "Your account is registered.");
        return "registration";
    }
}
