package com.socialportal.socialportal.services;


import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserRepository;
import com.socialportal.socialportal.repositories.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManager implements IUserManager {

    UserRepository userRepository;

    public static User user;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User getById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Long getUserId() {
        return findUserByEmail(user.getUsername()).getId();
    }

    @Override
    public List<User> findUsersByName(String name) {
        String[] words = name.split(" ");
        if (words.length == 1)
            return userRepository.findUserByFirstNameOrLastName(name, name);

        else if (words.length == 2) {
            List<User> users = userRepository.findUserByFirstNameOrLastName(words[0], words[1]);
            List<User> users2 = userRepository.findUserByFirstNameOrLastName(words[1], words[0]);

            Set<User> usersSet = new LinkedHashSet<>(users);
            usersSet.addAll(users2);

            List<User> allUsers = new LinkedList<>(usersSet);
            
            return allUsers;
        }
        // for more than 2 words, later
        else
            return null;
    }

    //temporary
    public Iterable<User> allUsers() {
        return userRepository.findAll();
    }
}
