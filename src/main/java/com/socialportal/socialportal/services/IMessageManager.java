package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;

import java.util.List;
import java.util.Set;

public interface IMessageManager {
    
    Set<User> getInterlocutors(Long id);

    List<Message> getAllMessages(Long id);

    Set<Message> getMessagesWithUser(Long senderId, Long receiverId);

    Set<Message> sortMessages(List<Message> messages);

    void sendMessage(Message message, Long receiverId);

    //temoprary
    Iterable<Message> allMessages();
}
