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
    public StatusManager(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public Long getIdOfAuthorOfStatus(Long id) {
        UserStatus userStatus = userStatusRepository.getUserStatusByStatusId(id);
        return userStatus.getAddingUser().getId();
    }

    @Override
    public UserStatus getUserStatus(Long id) {
        return userStatusRepository.getUserStatusByStatusId(id);
    }

    @Override
    public List<UserStatus> getStatuses(Long id) {
        return userStatusRepository.getUserStatusesByUserIdOrderByDateDesc(id);
    }

    @Override
    public void addNewStatus(UserStatus userStatus, Long userProfileId, User addingUser) {
        userStatus.setUserId(userProfileId);
        userStatus.setDate(new Date());
        userStatus.setAddingUser(addingUser);
        userStatusRepository.save(userStatus);
    }

    @Override
    public void deleteStatus(Long id) {
        userStatusRepository.delete(userStatusRepository.getUserStatusByStatusId(id));
    }

    @Override
    public void editUserStatus(Long id, String content) {
        UserStatus userStatus = getUserStatus(id);
        userStatus.setContent(content);
        userStatusRepository.save(userStatus);
    }

    //temporary
    @Override
    public Iterable<UserStatus> allStatus() {
        return userStatusRepository.findAll();
    }
}
