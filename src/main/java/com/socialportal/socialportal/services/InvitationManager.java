package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvitationManager {

    private InvitationRepository invitationRepository;
    private IUserManager userManager;

    @Autowired
    public InvitationManager(InvitationRepository invitationRepository, IUserManager userManager){
        this.invitationRepository = invitationRepository;
        this.userManager = userManager;
    }

    public void sendInvitation(Long userId, Long id) {
        Invitation invitation = new Invitation();
        invitation.setUserId(userId);
        invitation.setUser(userManager.getById(id));
        invitationRepository.save(invitation);
    }

    public void acceptInvitation(Long id){
        Invitation invitation = invitationRepository.getInvitationById(id);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.delete(invitationRepository.getInvitationById(id));
    }
}
