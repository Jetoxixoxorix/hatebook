package com.socialportal.socialportal;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.IStatusManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleUsers implements CommandLineRunner {

    @Autowired
    IUserManager userManager;

    @Autowired
    IStatusManager IStatusManager;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Jan", "Kox", "qwerty", "prosty@gmail.com");
        User user1 = new User("Piotr", "Prox", "qwerty", "prosty1@gmail.com");
        User user2 = new User("Magda", "Jet", "qwerty", "prosty2@gmail.com");
        userManager.register(user);
        userManager.register(user1);
        userManager.register(user2);

        UserStatus userStatus = new UserStatus("First status",  1L, new Date(), user);
        UserStatus userStatus1 = new UserStatus("Second status",  1L, new Date(), user1);
        IStatusManager.addNewStatus(userStatus1, 1L, user);
        IStatusManager.addNewStatus(userStatus, 1L, user1);
    }
}
