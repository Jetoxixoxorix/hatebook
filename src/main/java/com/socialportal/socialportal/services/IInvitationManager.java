package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;

import java.util.List;

public interface IInvitationManager {
    Invitation getInvitation(User receiver, User sender);
    List<Invitation> getReceivedInvitations(Long id);
    List<User> getUsersFromInvitationsList(Long id);
    List<Invitation> getSendInvitations(User user);
    List<User> getSendUsersFromInvitationsList(Long id);

    void sendInvitation(Long userId, Long id);
    void deleteInvitation(Long id);
}
