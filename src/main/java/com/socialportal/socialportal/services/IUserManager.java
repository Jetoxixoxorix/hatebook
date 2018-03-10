package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;

public interface IUserManager {
    void register(User user);
    User findEmail(User user);

    //temporary
    Iterable<User> allUsers();
}
