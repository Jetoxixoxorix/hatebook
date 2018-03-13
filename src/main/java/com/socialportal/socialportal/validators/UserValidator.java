package com.socialportal.socialportal.validators;


import com.socialportal.socialportal.errors.DifferentPasswordException;
import com.socialportal.socialportal.errors.ExistingEmailException;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator implements IUserValidator {

    IUserManager userManager;

    @Autowired
    public UserValidator(IUserManager userManager){
        this.userManager = userManager;
    }

    public void validateUser(User user) throws DifferentPasswordException, ExistingEmailException {
        checkEmail(user);
        checkPassword(user);
    }

    public void checkEmail(User user) throws ExistingEmailException {
        if (userManager.findUserByEmail(user.getUsername()) != null)
            throw new ExistingEmailException();
    }

    public void checkPassword(User user) throws DifferentPasswordException {
        if (!user.getConfirmPassword().equals(user.getPassword()))
            throw new DifferentPasswordException();
    }
}
