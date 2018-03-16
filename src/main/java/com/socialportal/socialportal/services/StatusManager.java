package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusManager {

    private UserStatusRepository userStatusRepository;

    public StatusManager(UserStatusRepository userStatusRepository){
        this.userStatusRepository = userStatusRepository;
    }

    public void addNewStatus(UserStatus userStatus, Long userId) {
        userStatus.setUserId(userId);
        userStatusRepository.save(userStatus);
    }

    //temporary
    public Iterable<UserStatus> allStatus() {
        return userStatusRepository.findAll();
    }

}
