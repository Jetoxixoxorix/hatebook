package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface IUserManager {
    User findUserByEmail(String email);
    User getById(Long id);
    Long getUserId();
    List<User> findUsersByName(String name);

    void register(User user);

    //temporary
    Iterable<User> allUsers();
}
