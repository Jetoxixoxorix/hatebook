package com.socialportal.socialportal.validators;


import com.socialportal.socialportal.errors.*;
import com.socialportal.socialportal.models.Invitation;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidator implements IUserValidator {

    private IUserManager userManager;
    private IFriendManager friendManager;
    private IInvitationManager invitationManager;

    @Autowired
    public UserValidator(IUserManager userManager, IFriendManager friendManager, IInvitationManager invitationManager, ICollectiveManager ICollectiveManager) {
        this.userManager = userManager;
        this.friendManager = friendManager;
        this.invitationManager = invitationManager;
    }

    public void validateUser(User user) throws DifferentPasswordException, ExistingEmailException {
        checkEmail(user);
        checkPassword(user);
    }

    public void checkEmail(User user) throws ExistingEmailException {
        if (userManager.findUserByEmail(user.getUsername()) != null)
            throw new ExistingEmailException();
    }

    public void checkPassword(User user) throws DifferentPasswordException {
        if (!user.getConfirmPassword().equals(user.getPassword()))
            throw new DifferentPasswordException();
    }

    public void deletePrivilege(Long loggedUser, Long statusUser, Long profileUser) throws HasPrivilegeException {
        if (loggedUser != profileUser && loggedUser != statusUser)
            throw new HasPrivilegeException();
    }

    public void editPrivilege(Long loggedUser, Long statusUser) throws HasPrivilegeException {
        if (loggedUser != statusUser)
            throw new HasPrivilegeException();
    }

    public void checkAddingFriend(Long loggedUser, Long addedFriend) throws SameUserException, HasThisFriendException {
        checkSameUser(loggedUser, addedFriend);
        checkIsFriend(loggedUser, addedFriend);
    }

    public void checkSendInvitation(Long loggedUser, Long addedFriend) throws HasInvitationException, HasThisFriendException, SameUserException {
        checkSameUser(loggedUser, addedFriend);
        checkIsFriend(loggedUser, addedFriend);
        checkHasInvitation(loggedUser, addedFriend);
        checkHasInvitation(addedFriend, loggedUser);
    }

    private void checkHasInvitation(Long loggedUser, Long addedFriend) throws HasInvitationException {
        List<User> invitationList = invitationManager.getSendersOfInvitations(loggedUser);
        if (invitationList.contains(userManager.getUserById(addedFriend)))
            throw new HasInvitationException();
    }

    public void checkIsFriend(Long loggedUserId, Long addedFriend) throws HasThisFriendException {
        List<User> friendsList = friendManager.getUsersFromFriendsOfUser(loggedUserId);
        if (friendsList.contains(userManager.getUserById(addedFriend)))
            throw new HasThisFriendException();
    }

    public void checkSameUser(Long loggedUser, Long addedFriend) throws SameUserException {
        if (loggedUser == addedFriend)
            throw new SameUserException();
    }

    public void checkIfUserInvitation(Long invitationId) throws NotYourInvitationException {
        Invitation invitation = invitationManager.getInvitationById(invitationId);
        if (!(invitation.getSender() == userManager.getUserById(userManager.getUserId()) || invitation.getReceiver() == userManager.getUserById(userManager.getUserId())))
            throw new NotYourInvitationException();
    }
}

