package com.socialportal.socialportal.errors;

public class DifferentPasswordException extends Exception{
    @Override
    public String getMessage() {
        return "Passwords aren't the same.";
    }
}
