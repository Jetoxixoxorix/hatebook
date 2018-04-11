package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface ICommentManager {
    List<UserComment> getUserComments(Long id);
    Long getIdOfAuthorOfComment(Long id);
    UserComment getComment(Long id);

    void addNewComment(UserComment userComment, Long userProfileId, UserStatus userStatus, User user);
    void deleteComment(Long id);
    void editComment(Long id, String content);
    void deleteComments(UserStatus userStatus);

    //temporary
    Iterable<UserComment> allComments();
}
