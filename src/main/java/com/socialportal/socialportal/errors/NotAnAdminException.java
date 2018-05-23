package com.socialportal.socialportal.errors;

public class NotAnAdminException extends Exception {
    @Override
    public String getMessage() {
        return "Only group admin can do this";
    }
}
