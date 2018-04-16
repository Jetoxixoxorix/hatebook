package com.socialportal.socialportal.controllers;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.services.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MessageController {

    private MessageManager messageManager;

    @Autowired
    public MessageController(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public String getMessages(Model model, @PathVariable("receiverId") Long receiverId, @PathVariable Long senderId){
        model.addAttribute("messages", messageManager.getMessages(receiverId, senderId));
        return "messages";
    }


    //{senderId}/{receiverId}"
    //, @PathVariable("receiverId") Long receiverId, @PathVariable Long senderId
    @GetMapping("/message")
    public String getMessage(Model model){
        model.addAttribute("message", new Message());
        return "sendingMessage";
    }

    /*, @PathVariable("receiverId") Long receiverId, @PathVariable Long senderId*/
    @PostMapping("/sendmessage/{receiverId}/{senderId}")
    public String sendMessage(Model model){

        return "messages";
    }
}
