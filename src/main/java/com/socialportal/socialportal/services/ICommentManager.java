package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface ICommentManager {
    List<UserComment> getUserComments(Long id);
    void addNewComment(UserComment userComment, Long userProfileId, UserStatus userStatus, User user);
    void deleteComment(Long id);
    Long getAuthorOfComment(Long id);

    //temporary
    Iterable<UserComment> allComments();
}
