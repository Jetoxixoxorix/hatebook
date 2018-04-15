package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface IStatusManager {
    List<UserStatus> getStatuses(Long id);
    Long getIdOfAuthorOfStatus(Long id);
    UserStatus getUserStatus(Long id);

    void addNewStatus(UserStatus userStatus, Long userProfileId, User addingUser);
    void deleteStatus(Long id);
    void editUserStatus(Long id, String content);

    void addNewStatus(UserStatus userStatus);
    //temporary
    Iterable<UserStatus> allStatus();
}
