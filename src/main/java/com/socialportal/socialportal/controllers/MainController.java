package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.errors.HasPrivilegeException;
import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.*;
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
    private ICommentManager commentManager;
    private IMessageManager messageManager;

    @Autowired
    public MainController(IUserManager userManager, IStatusManager statusManager, IUserValidator userValidator, ICommentManager commentManager, IMessageManager messageManager) {
        this.userManager = userManager;
        this.statusManager = statusManager;
        this.userValidator = userValidator;
        this.commentManager = commentManager;
        this.messageManager = messageManager;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginData", new User());
        return "login";
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
        model.addAttribute("userProfile", userManager.getUserById(id));

        model.addAttribute("addComment", new UserComment());
        model.addAttribute("comments", commentManager.getUserComments(id));

        if (userManager.getUserById(id) == null) {
            model.addAttribute("nonExistingUser", "There is no such user.");
        }
        return "userProfile";
    }

    //statuses

    @PostMapping("/userprofile/{id}")
    public String addStatus(@ModelAttribute("add") UserStatus userStatus, @PathVariable("id") Long id, Model model) {
        statusManager.addNewStatus(userStatus, id, userManager.getUserById(userManager.getUserId()));
        return getUserProfile(id, model);
    }

    @PostMapping("/userprofile/{userid}/deletestatus/{statusid}")
    public String deleteStatus(Model model, @PathVariable("statusid") Long statusId, @PathVariable("userid") Long userId) {
        try {
            userValidator.deletePrivilege(userManager.getUserId(), statusManager.getIdOfAuthorOfStatus(statusId), statusManager.getUserStatus(statusId).getUserId());
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "errors";
        }

        commentManager.deleteComments(statusManager.getUserStatus(statusId));
        statusManager.deleteStatus(statusId);
        return getUserProfile(userId, model);
    }

    @GetMapping("/userprofile/{userid}/editstatus/{id}")
    public String editStatus(Model model, @PathVariable("id") Long id, @PathVariable("userid") Long userId) {
        try {
            userValidator.editPrivilege(userManager.getUserId(), statusManager.getIdOfAuthorOfStatus(id));
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "errors";
        }

        model.addAttribute("userStatus", statusManager.getUserStatus(id));
        return "edit";
    }

    @PostMapping("/userprofile/{userid}/editstatus/{id}")
    public String editStatus(Model model, @PathVariable("id") Long id, @PathVariable("userid") Long userId, String content) {
        statusManager.editUserStatus(id, content);
        return getUserProfile(userId, model);
    }

    //comments

    @PostMapping("/userprofile/{id}/addcomment/{statusId}")
    public String addComment(@PathVariable("id") Long id, @PathVariable("statusId") Long statusId, @ModelAttribute("addComment") UserComment userComment, Model model) {
        commentManager.addNewComment(userComment, id, statusManager.getUserStatus(statusId), userManager.getUserById(userManager.getUserId()));
        return getUserProfile(id, model);
    }

    @PostMapping("/userprofile/{id}/deletecomment/{commentId}")
    public String deleteComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId, Model model) {
        try {
            userValidator.deletePrivilege(userManager.getUserId(), commentManager.getIdOfAuthorOfComment(commentId), commentManager.getComment(commentId).getUserId());
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "errors";
        }

        commentManager.deleteComment(commentId);
        return getUserProfile(id, model);
    }

    @GetMapping("/userprofile/{userid}/editcomment/{id}")
    public String editComment(Model model, @PathVariable("id") Long commentId) {
        try {
            userValidator.editPrivilege(userManager.getUserId(), commentManager.getIdOfAuthorOfComment(commentId));
        } catch (HasPrivilegeException e) {
            model.addAttribute("privilege", e.getMessage());
            return "errors";
        }

        model.addAttribute("editComment", commentManager.getComment(commentId));
        return "editComment";
    }

    @PostMapping("/userprofile/{userid}/editcomment/{id}")
    public String editComment(Model model, @PathVariable("userid") Long userid, String content, @PathVariable("id") Long commentId) {
        commentManager.editComment(commentId, content);
        return getUserProfile(userid, model);
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

    //temporary
    @GetMapping("/comments")
    public @ResponseBody
    Iterable<UserComment> allComments() {
        return commentManager.allComments();
    }

    //temporary
    @GetMapping("allmessages")
    public @ResponseBody
    Iterable<Message> allMessages() {
        return messageManager.allMessages();
    }
}
