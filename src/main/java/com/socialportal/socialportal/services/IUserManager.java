package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;

public interface IUserManager {
    void register(User user);
    User findUserByEmail(String email);
    User getById(Long id);
    Long getUserId();

    //temporary
    Iterable<User> allUsers();
}
