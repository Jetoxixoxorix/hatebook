package com.socialportal.socialportal.errors;

public class NotYourInvitationException extends Exception {

    @Override
    public String getMessage() {
        return "You can't delete someone else invitation";
    }
}
