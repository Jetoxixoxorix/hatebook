package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;

public interface IUserManager {
    void register(User user);

    User findUserByEmail(String email);

    //temporary
    Iterable<User> allUsers();
}
