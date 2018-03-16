package com.socialportal.socialportal.security;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import com.socialportal.socialportal.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    IUserManager userManager;

    @Autowired
    public MyUserDetailService(IUserManager userManager){
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userManager.findUserByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        UserManager.user = user;
        return new MyUserPrincipal(user);
    }

}
