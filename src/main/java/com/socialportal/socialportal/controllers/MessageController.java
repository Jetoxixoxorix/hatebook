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

    @GetMapping("/messages/{id}/{interlocutorId}")
    public String getMessages(Model model, @PathVariable("id") Long id, @PathVariable("interlocutorId") Long interlocutorId) {
        model.addAttribute("interlocutors", messageManager.getInterlocutors(userManager.getUserId()));
        model.addAttribute("messages", messageManager.getMessagesWithUser(id, interlocutorId));
        model.addAttribute("loggedUserId", userManager.getUserId());
        model.addAttribute("interlocutor", userManager.getUserById(interlocutorId));
        return "messages";
    }

    @GetMapping("/messages")
    public String getInterlocutors(Model model){
        model.addAttribute("interlocutors", messageManager.getInterlocutors(userManager.getUserId()));
        model.addAttribute("loggedUserId", userManager.getUserId());
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
        return getMessage(model, senderId, receiverId);
    }
}
