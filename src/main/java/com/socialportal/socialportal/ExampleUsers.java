package com.socialportal.socialportal;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleUsers implements CommandLineRunner {

    @Autowired
    IUserManager userManager;

    @Autowired
    StatusManager statusManager;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Jan", "Kox", "qwerty", "prosty@gmail.com");
        User user1 = new User("Piotr", "Prox", "qwerty", "prosty1@gmail.com");
        User user2 = new User("Magda", "Jet", "qwerty", "prosty2@gmail.com");
        userManager.register(user);
        userManager.register(user1);
        userManager.register(user2);

        UserStatus userStatus = new UserStatus("ffqfwqfwqfqwf",  1L, new Date());
        UserStatus userStatus1 = new UserStatus("ffqfwqfwqfqwf",  1L, new Date());
        statusManager.addNewStatus(userStatus, 1L);
        statusManager.addNewStatus(userStatus1, 1L);

    }
}
