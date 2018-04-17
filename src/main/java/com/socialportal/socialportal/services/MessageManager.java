package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    public void sendMessage(Message message, Long senderId, Long receiverId) {
        message.setSender(userManager.getUserById(senderId));
        message.setReceiver(userManager.getUserById(receiverId));
        message.setDate(new Date());
        messageRepository.save(message);
    }

    public List<Message> getMessages(Long receiverId, Long senderId) {
        List<Message> messages = messageRepository.getMessagesByReceiverOrSender(userManager.getUserById(receiverId), userManager.getUserById(senderId));
        return messages;
    }

    //temoprary
    public Iterable<Message> allMessages() {
        return messageRepository.findAll();
    }
}
