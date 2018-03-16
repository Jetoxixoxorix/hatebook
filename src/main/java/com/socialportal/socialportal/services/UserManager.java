package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserRepository;
import com.socialportal.socialportal.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements IUserManager {

    UserRepository userRepository;
    //UserStatusRepository userStatusRepository;

    public static User user;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserManager(UserRepository userRepository, UserStatusRepository userStatusRepository){
        this.userRepository = userRepository;
        //this.userStatusRepository = userStatusRepository;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getById(Long id){
        return userRepository.findUserById(id);
    }

    @Override
    public Long getUserId() {
        return findUserByEmail(user.getUsername()).getId();
    }

    @Override

    //temporary
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }

}
