package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.DifferentPasswordException;
import com.socialportal.socialportal.errors.ExistingEmailException;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.UserManager;
import com.socialportal.socialportal.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    IUserManager userManager;

    @Autowired
    UserValidator userValidator;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
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
        return "registration";
    }

    //temporary
    @GetMapping("/users")
    public @ResponseBody Iterable<User> allUsers(){
        return userManager.allUsers();
    }
}
