package com.socialportal.socialportal.errors;

public class NotYourMessagesException extends Exception {

    @Override
    public String getMessage() {
        return "Can't check someone else messages";
    }
}
