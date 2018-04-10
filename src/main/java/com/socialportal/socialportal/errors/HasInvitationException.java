package com.socialportal.socialportal.errors;

public class HasInvitationException extends Exception {

    @Override
    public String getMessage() {
        return "Invitation is already send between those users.";
    }
}
