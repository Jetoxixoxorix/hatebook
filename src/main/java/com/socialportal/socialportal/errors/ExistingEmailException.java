package com.socialportal.socialportal.errors;

public class ExistingEmailException extends Exception {

    @Override
    public String getMessage() {
        return "This email already exists";
    }
}
