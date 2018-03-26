package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusManager implements IStatusManager {

    private UserStatusRepository userStatusRepository;

    @Autowired
    public StatusManager(UserStatusRepository userStatusRepository){
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public void addNewStatus(UserStatus userStatus, Long userProfileId, User addingUserId) {
        userStatus.setUserId(userProfileId);
        userStatus.setDate(new Date());
        userStatus.setAddingUserId(addingUserId);
        userStatusRepository.save(userStatus);
    }

    @Override
    public List<UserStatus> getStatuses(Long id){
        return userStatusRepository.getUserStatusesByUserIdOrderByDateDesc(id);
    }

    //temporary
    @Override
    public Iterable<UserStatus> allStatus() {
        return userStatusRepository.findAll();
    }
}
