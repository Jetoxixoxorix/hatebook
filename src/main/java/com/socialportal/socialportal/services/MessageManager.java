package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageManager {

    private MessageRepository messageRepository;
    private UserManager userManager;

    @Autowired
    public MessageManager(MessageRepository messageRepository, UserManager userManager) {
        this.messageRepository = messageRepository;
        this.userManager = userManager;
    }


    public void sendMessage() {

    }

    public List<Message> getMessages(Long receiverId, Long senderId) {
        List<Message> messages = messageRepository.getMessagesByReceiverOrSender(userManager.getUserById(receiverId), userManager.getUserById(senderId));
        return messages;
    }
}
