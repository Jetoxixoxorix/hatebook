package com.socialportal.socialportal.errors;

public class SameUserException extends Exception {
    @Override
    public String getMessage() {
        return "Can't add yourself as a friend.";
    }
}
