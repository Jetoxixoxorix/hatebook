package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface IStatusManager {
    void addNewStatus(UserStatus userStatus, Long userProfileId, User addingUser);
    void deleteStatus(Long id);
    List<UserStatus> getStatuses(Long id);

    //temporary
    Iterable<UserStatus> allStatus();
}
