package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.services.IMessageManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class MessageController {

    private IMessageManager messageManager;
    private IUserManager userManager;

    @Autowired
    public MessageController(IMessageManager messageManager, IUserManager userManager) {
        this.messageManager = messageManager;
        this.userManager = userManager;
    }

    @GetMapping("/messages/{id}/{interlocutorId}")
    public String getMessages(@PathVariable("id") Long id, @PathVariable("interlocutorId") Long interlocutorId, Model model) {
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

    @GetMapping("/message/{receiverId}")
    public String getMessage(@PathVariable("receiverId") Long receiverId, Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("sender", userManager.getUserById(userManager.getUserId()));
        model.addAttribute("receiver", userManager.getUserById(receiverId));
        return "sendingMessage";
    }

    @PostMapping("/sendmessage/{receiverId}")
    public String sendMessage(@ModelAttribute("message") Message message, @PathVariable("receiverId") Long receiverId, Model model) {
        messageManager.sendMessage(message, receiverId);
        return getMessage(receiverId, model);
    }
}
