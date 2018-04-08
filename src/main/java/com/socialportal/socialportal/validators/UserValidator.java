package com.socialportal.socialportal.validators;


import com.socialportal.socialportal.errors.*;
import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IFriendManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidator implements IUserValidator {

    private IUserManager userManager;
    private IFriendManager friendManager;

    @Autowired
    public UserValidator(IUserManager userManager, IFriendManager friendManager) {
        this.userManager = userManager;
        this.friendManager = friendManager;
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

    public void checkPrivilege(Long loggedUser, Long statusUser, Long profileUser) throws HasPrivilegeException {
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

    public void checkIsFriend(Long loggedUserId, Long addedFriend) throws HasThisFriendException {
        List<Friend> friendsList = friendManager.getFriendsList(loggedUserId);
        for (Friend f : friendsList) {
            if (f.getFriend().getId() == addedFriend)
                throw new HasThisFriendException();
        }
    }

    public void checkSameUser(Long loggedUser, Long addedFriend) throws SameUserException {
        if (loggedUser == addedFriend)
            throw new SameUserException();
    }
}

