package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InvitationManager implements IInvitationManager {

    private InvitationRepository invitationRepository;
    private IUserManager userManager;

    @Autowired
    public InvitationManager(InvitationRepository invitationRepository, IUserManager userManager){
        this.invitationRepository = invitationRepository;
        this.userManager = userManager;
    }

    public Invitation getInvitationById(Long id){
        return invitationRepository.getInvitationById(id);
    }

    public Invitation getInvitation(User receiver, User sender){
        if(invitationRepository.getInvitationByReceiverAndSender(receiver, sender) != null)
            return invitationRepository.getInvitationByReceiverAndSender(receiver, sender);
        else
            return invitationRepository.getInvitationByReceiverAndSender(sender, receiver);
    }

    public List<Invitation> getReceivedInvitations(Long id){
        return invitationRepository.getInvitationsByReceiver(userManager.getUserById(id));
    }

    public List<User> getSendersOfInvitations(Long id) {
        List<Invitation> invitationList = getReceivedInvitations(id);
        List<User> users = new LinkedList<>();
        for (Invitation invitation : invitationList) {
            users.add(invitation.getSender());
        }

        return users;
    }

    public List<Invitation> getSendInvitations(Long id){
        return invitationRepository.getInvitationsBySender(userManager.getUserById(id));
    }

    public List<User> getReceiversOfInvitations(Long id) {
        List<Invitation> invitationList = getSendInvitations(id);
        List<User> users = new LinkedList<>();
        for (Invitation invitation : invitationList) {
            users.add(invitation.getReceiver());
        }

        return users;
    }

    public void sendInvitation(Long userId, Long id) {
        Invitation invitation = new Invitation();
        invitation.setReceiver(userManager.getUserById(userId));
        invitation.setSender(userManager.getUserById(id));
        invitationRepository.save(invitation);
    }

    public void deleteInvitation(Long id) {
        invitationRepository.delete(invitationRepository.getInvitationById(id));
    }
}
