package com.socialportal.socialportal;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleUsers implements CommandLineRunner {

    @Autowired
    IUserManager userManager;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFirstName("Jan");
        user.setLastName("Kox");
        user.setEmail("prosty@gmail.com");
        user.setPassword("qwerty");
        userManager.register(user);
    }
}
