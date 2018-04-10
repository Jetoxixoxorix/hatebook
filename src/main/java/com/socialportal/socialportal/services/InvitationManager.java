package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InvitationManager {

    private InvitationRepository invitationRepository;
    private IUserManager userManager;

    @Autowired
    public InvitationManager(InvitationRepository invitationRepository, IUserManager userManager){
        this.invitationRepository = invitationRepository;
        this.userManager = userManager;
    }

    public List<Invitation> getReceivedInvitations(Long id){
        return invitationRepository.getInvitationsByReceiver(userManager.getById(id));
    }

    public List<User> getUsersFromInvitationsList(Long id) {
        List<Invitation> invitationList = getReceivedInvitations(id);
        List<User> users = new LinkedList<>();
        for (Invitation invitation : invitationList) {
            users.add(invitation.getSendUser());
        }

        return users;
    }

    public List<Invitation> getSendInvitations(User user){
        return invitationRepository.getInvitationsBySendUser(user);
    }

    public List<User> getSendUsersFromInvitationsList(Long id) {
        List<Invitation> invitationList = getSendInvitations(userManager.getById(id));
        List<User> users = new LinkedList<>();
        for (Invitation invitation : invitationList) {
            users.add(invitation.getReceiver());
        }

        return users;
    }

    public void sendInvitation(Long userId, Long id) {
        Invitation invitation = new Invitation();
        invitation.setReceiver(userManager.getById(userId));
        invitation.setSendUser(userManager.getById(id));
        invitationRepository.save(invitation);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.delete(invitationRepository.getInvitationById(id));
    }
}
