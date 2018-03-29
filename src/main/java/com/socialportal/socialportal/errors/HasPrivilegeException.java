package com.socialportal.socialportal.errors;

public class HasPrivilegeException extends Exception {
    @Override
    public String getMessage() {
        return "Can't do this action.";
    }
}
