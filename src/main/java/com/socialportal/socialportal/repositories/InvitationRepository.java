package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
    Invitation getInvitationById(Long id);
    List<Invitation> getInvitationsByReceiver(User user);
    List<Invitation> getInvitationsBySendUser(User user);
    Invitation getInvitationByReceiverAndSendUser(User receiver, User sender);
}
