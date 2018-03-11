package com.socialportal.socialportal.security;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    IUserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userManager.findUserByEmail(email);
        if (user == null)
            throw new UsernameNotFoundException(email);

        return new MyUserPrincipal(user);
    }
}
