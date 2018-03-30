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
        User user3 = new User("Jan", "Niezbedny", "qwerty", "prosty3@gmail.com");
        User user4 = new User("Jan", "Zbedny", "qwerty", "prosty4@gmail.com");
        User user5 = new User("Piotr", "Kox", "qwerty", "prosty5@gmail.com");
        userManager.register(user);
        userManager.register(user1);
        userManager.register(user2);
        userManager.register(user3);
        userManager.register(user4);
        userManager.register(user5);

        UserStatus userStatus = new UserStatus("First status",  1L, new Date(), user);
        UserStatus userStatus1 = new UserStatus("Second status",  1L, new Date(), user1);
        UserStatus userStatus2 = new UserStatus("Third status",  2L, new Date(), user1);
        UserStatus userStatus3 = new UserStatus("Fourth status",  2L, new Date(), user2);
        UserStatus userStatus4 = new UserStatus("Fifth status",  2L, new Date(), user3);
        UserStatus userStatus5 = new UserStatus("Sixth status",  3L, new Date(), user2);
        UserStatus userStatus6 = new UserStatus("Seventh status",  3L, new Date(), user3);
        IStatusManager.addNewStatus(userStatus, 1L, user);
        IStatusManager.addNewStatus(userStatus1, 1L, user1);
        IStatusManager.addNewStatus(userStatus2, 2L, user1);
        IStatusManager.addNewStatus(userStatus3, 2L, user2);
        IStatusManager.addNewStatus(userStatus4, 2L, user3);
        IStatusManager.addNewStatus(userStatus5, 3L, user2);
        IStatusManager.addNewStatus(userStatus6, 3L, user3);
    }
}
