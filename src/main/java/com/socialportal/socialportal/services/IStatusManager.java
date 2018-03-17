package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface IStatusManager {
    void addNewStatus(UserStatus userStatus, Long userId);

    List<UserStatus> getStatuses(Long id);

    //temporary
    Iterable<UserStatus> allStatus();
}
