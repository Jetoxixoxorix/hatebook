package com.socialportal.socialportal.errors;

public class HasThisFriendException extends Exception {
    @Override
    public String getMessage() {
        return "You have this friend already.";
    }
}
