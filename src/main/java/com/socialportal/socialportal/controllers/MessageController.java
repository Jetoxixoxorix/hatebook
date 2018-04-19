package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MessageController {

    private MessageManager messageManager;
    private IUserManager userManager;

    @Autowired
    public MessageController(MessageManager messageManager, IUserManager userManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public String getMessages(Model model, @PathVariable("receiverId") Long receiverId, @PathVariable Long senderId) {
        model.addAttribute("people", messageManager);
        //model.addAttribute("messages", messageManager.getMessages(receiverId, senderId));
        return "messages";
    }

    @GetMapping("/messages")
    public String getPeople(Model model){
        model.addAttribute("people", messageManager.getPeople(userManager.getUserId()));
        return "messages";
    }

    @GetMapping("/message/{senderId}/{receiverId}")
    public String getMessage(Model model, @PathVariable Long senderId, @PathVariable("receiverId") Long receiverId) {
        model.addAttribute("message", new Message());
        model.addAttribute("sender", userManager.getUserById(senderId));
        model.addAttribute("receiver", userManager.getUserById(receiverId));
        return "sendingMessage";
    }

    @PostMapping("/sendmessage/{senderId}/{receiverId}")
    public String sendMessage(@ModelAttribute("message") Message message, Model model, @PathVariable Long senderId, @PathVariable("receiverId") Long receiverId) {
        messageManager.sendMessage(message, senderId, receiverId);
        return getPeople(model);
    }
}
