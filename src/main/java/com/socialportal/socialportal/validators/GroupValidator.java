package com.socialportal.socialportal.validators;

import com.socialportal.socialportal.errors.NotAMemberOfGroup;
import com.socialportal.socialportal.errors.NotAnAdminException;
import com.socialportal.socialportal.services.ICollectiveManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GroupValidator implements IGroupValidator {

    private ICollectiveManager ICollectiveManager;
    private IUserManager userManager;

    @Autowired
    public GroupValidator(ICollectiveManager ICollectiveManager, IUserManager userManager) {
        this.ICollectiveManager = ICollectiveManager;
        this.userManager = userManager;
    }

    @Override
    public void hasAdminPrivilige(Long groupId, Long loggedUser) throws NotAnAdminException {
        if (!ICollectiveManager.isAdmin(ICollectiveManager.getGroup(groupId), userManager.getUserById(loggedUser)))
            throw new NotAnAdminException();
    }

    @Override
    public void isAMemberOfGroup(Long groupId, Long userId) throws NotAMemberOfGroup {
        if (!ICollectiveManager.isMemberOfGroup(userManager.getUserById(userId), ICollectiveManager.getGroup(groupId)))
            throw new NotAMemberOfGroup();
    }

    @Override
    public String isAdminAndIsMember(Long groupId, Long userId, Model model) {
        try {
            isAMemberOfGroup(groupId, userId);
            hasAdminPrivilige(groupId, userManager.getUserId());
        } catch (NotAnAdminException e) {
            model.addAttribute("notAnAdmin", e.getMessage());
            return "errors";
        } catch (NotAMemberOfGroup e) {
            model.addAttribute("notAMember", e.getMessage());
            return "errors";
        }

        return null;
    }
}
