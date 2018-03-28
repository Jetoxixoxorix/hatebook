package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface IUserManager {
    void register(User user);
    User findUserByEmail(String email);
    User getById(Long id);
    Long getUserId();
    List<User> findUsersByName(String name);

    //temporary
    Iterable<User> allUsers();
}
