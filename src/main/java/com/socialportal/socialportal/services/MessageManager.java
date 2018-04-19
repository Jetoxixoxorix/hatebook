package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<User> getPeople(Long id){
        List<Message> messages = getMessages(id);
        Set<User> users = new HashSet<>();

        for(Message message : messages) {
            if(message.getSender() == message.getReceiver())
                users.add(message.getReceiver());
            else if(message.getSender() != userManager.getUserById(userManager.getUserId())){
                users.add(message.getSender());
            }
            else if(message.getReceiver() != userManager.getUserById(userManager.getUserId())){
                users.add(message.getReceiver());
            }
        }
        
        return users;
    }

    public List<Message> getMessages(Long id) {
        List<Message> messages = messageRepository.getMessagesByReceiverOrSender(userManager.getUserById(id), userManager.getUserById(id));
        return messages;
    }

    //temoprary
    public Iterable<Message> allMessages() {
        return messageRepository.findAll();
    }
}
