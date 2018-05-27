package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;

import java.util.List;

public interface IInvitationManager {
    Invitation getInvitation(User receiver, User sender);

    List<Invitation> getReceivedInvitations(Long id);

    List<User> getSendersOfInvitations(Long id);

    List<Invitation> getSendInvitations(Long id);

    List<User> getReceiversOfInvitations(Long id);

    Invitation getInvitationById(Long id);

    void sendInvitation(Long userId, Long id);

    void deleteInvitation(Long id);
}
