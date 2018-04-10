package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Friend;
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

    public List<Invitation> getInvitations(Long id){
        return invitationRepository.getInvitationsByReceiver(userManager.getById(id));
    }

    public List<User> getUsersFromInvitationsList(Long id) {
        List<Invitation> invitationList = getInvitations(id);
        List<User> users = new LinkedList<>();
        for (Invitation invitation : invitationList) {
            users.add(invitation.getSendUser());
        }

        return users;
    }

    public List<Invitation> getSendInvitations(User user){
        return invitationRepository.getInvitationsBySendUser(user);
    }

    public void sendInvitation(Long userId, Long id) {
        Invitation invitation = new Invitation();
        invitation.setReceiver(userManager.getById(userId));
        invitation.setSendUser(userManager.getById(id));
        invitationRepository.save(invitation);
    }



    public void acceptInvitation(Long id){
        Invitation invitation = invitationRepository.getInvitationById(id);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.delete(invitationRepository.getInvitationById(id));
    }
}
