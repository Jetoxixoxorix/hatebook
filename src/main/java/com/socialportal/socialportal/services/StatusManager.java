package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusManager {

    private UserStatusRepository userStatusRepository;

    @Autowired
    public StatusManager(UserStatusRepository userStatusRepository){
        this.userStatusRepository = userStatusRepository;
    }

    public void addNewStatus(UserStatus userStatus, Long userId) {
        userStatus.setUserId(userId);
        userStatusRepository.save(userStatus);
    }

    public List<UserStatus> getStatuses(Long id){
        return userStatusRepository.getUserStatusesByUserId(id);
    }



    //temporary
    public Iterable<UserStatus> allStatus() {
        return userStatusRepository.findAll();
    }
}
