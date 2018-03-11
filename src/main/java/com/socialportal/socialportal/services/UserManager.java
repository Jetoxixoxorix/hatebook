package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUserManager {

    @Autowired
    UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    //temporary
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }
}
