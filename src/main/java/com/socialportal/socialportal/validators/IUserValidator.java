package com.socialportal.socialportal.validators;

import com.socialportal.socialportal.errors.*;
import com.socialportal.socialportal.models.User;

public interface IUserValidator {
    void validateUser(User user) throws DifferentPasswordException, ExistingEmailException;

    void checkEmail(User user) throws ExistingEmailException;

    void checkPassword(User user) throws DifferentPasswordException;

    void checkPrivilege(Long loggedUser, Long statusUser, Long profileUser) throws HasPrivilegeException;

    void editPrivilege(Long loggedUser, Long statusUser) throws HasPrivilegeException;

    void checkAddingFriend(Long loggedUser, Long addedFriend) throws SameUserException, HasThisFriendException;
}
