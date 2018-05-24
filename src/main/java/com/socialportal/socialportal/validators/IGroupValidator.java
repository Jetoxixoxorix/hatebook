package com.socialportal.socialportal.validators;

import com.socialportal.socialportal.errors.NotAMemberOfGroup;
import com.socialportal.socialportal.errors.NotAnAdminException;
import org.springframework.ui.Model;

public interface IGroupValidator {
    void hasAdminPrivilige(Long groupId, Long loggedUser) throws NotAnAdminException;

    void isAMemberOfGroup(Long groupId, Long userId) throws NotAMemberOfGroup;

    String isAdminAndIsMember(Long groupId, Long userId, Model model);
}
