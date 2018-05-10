package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Message;
import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> getMessagesByReceiverOrSender(User receiver, User sender);

    List<Message> getMessagesByReceiverAndSender(User receiver, User sender);
}
