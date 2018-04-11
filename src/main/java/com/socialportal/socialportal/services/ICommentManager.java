package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;

import java.util.List;

public interface ICommentManager {
    void addNewComment(UserComment userComment, Long userProfileId, UserStatus userStatus, User user);
    void deleteComment(Long id);
    void editComment(Long id, String content);

    List<UserComment> getUserComments(Long id);
    Long getIdOfAuthorOfComment(Long id);
    UserComment getComment(Long id);

    //temporary
    Iterable<UserComment> allComments();
}
