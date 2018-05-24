package com.socialportal.socialportal.errors;

public class NotAMemberOfGroup extends Exception {

    @Override
    public String getMessage() {
        return "This user is not a member of the group";
    }
}
