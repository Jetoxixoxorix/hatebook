package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
