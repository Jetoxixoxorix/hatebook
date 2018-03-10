package com.socialportal.socialportal.validators;


import com.socialportal.socialportal.errors.ExistingEmailException;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    IUserManager userManager;

    public void checkEmail(User user) throws ExistingEmailException {
        if(userManager.findUserByEmail(user) != null)
            throw new ExistingEmailException();
    }
}
