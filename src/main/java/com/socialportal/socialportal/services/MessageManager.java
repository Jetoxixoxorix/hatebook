package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Set<User> getInterlocutors(Long id){
        List<Message> messages = getAllMessages(id);
        Set<User> interlocutors = new HashSet<>();

        for(Message message : messages) {
            if(message.getSender() == message.getReceiver())
                interlocutors.add(message.getReceiver());
            else if(message.getSender() != userManager.getUserById(userManager.getUserId())){
                interlocutors.add(message.getSender());
            }
            else if(message.getReceiver() != userManager.getUserById(userManager.getUserId())){
                interlocutors.add(message.getReceiver());
            }
        }

        return interlocutors;
    }

    public List<Message> getAllMessages(Long id) {
        List<Message> messages = messageRepository.getMessagesByReceiverOrSender(userManager.getUserById(id), userManager.getUserById(id));
        return messages;
    }

    public Set<Message> getMessagesWithUser(Long senderId, Long receiverId){
        //List<Message> allMessages = getAllMessages(userManager.getUserId());
        List<Message> messagesWithUser = messageRepository.getMessagesByReceiverAndSender(userManager.getUserById(receiverId), userManager.getUserById(senderId));
        List<Message> messagesWithUser2 = messageRepository.getMessagesByReceiverAndSender(userManager.getUserById(senderId), userManager.getUserById(receiverId));

        Set<Message> messages = new LinkedHashSet<>(messagesWithUser);

        for (Message message : messagesWithUser2){
            messages.add(message);
        }

        return messages;
    }

    //temoprary
    public Iterable<Message> allMessages() {
        return messageRepository.findAll();
    }
}
