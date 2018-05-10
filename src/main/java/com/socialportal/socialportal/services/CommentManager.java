package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.Date;
import java.util.List;

@Service
public class CommentManager implements ICommentManager {

    private UserCommentRepository userCommentRepository;

    @Autowired
    public CommentManager(UserCommentRepository userCommentRepository){
        this.userCommentRepository = userCommentRepository;
    }

    @Override
    public List<UserComment> getUserComments(Long id) {
        return userCommentRepository.getUserCommentsByUserId(id);
    }

    @Override
    public Long getIdOfAuthorOfComment(Long id) {
        UserComment comment = getComment(id);
        return comment.getAddingUser().getId();
    }

    @Override
    public UserComment getComment(Long id) {
        return userCommentRepository.getUserCommentByCommentId(id);
    }

    @Override
    public void addNewComment(UserComment userComment, Long userProfileId, UserStatus userStatus, User user) {
        userComment.setUserId(userProfileId);
        userComment.setDate(new Date());
        userComment.setUserStatus(userStatus);
        userComment.setAddingUser(user);
        userCommentRepository.save(userComment);
    }

    @Override
    public void deleteComment(Long id){
        userCommentRepository.delete(userCommentRepository.getUserCommentByCommentId(id));
    }

    @Override
    public void editComment(Long id, String content) {
        UserComment userComment = getComment(id);
        userComment.setContent(content);
        userCommentRepository.save(userComment);
    }

    @Override
    public void deleteComments(UserStatus userStatus) {
        List<UserComment> comments = userCommentRepository.getCommentsByUserStatus(userStatus);
        for (UserComment comment : comments){
            userCommentRepository.delete(comment);
        }
    }

    @Override
    public void addNewComment(UserComment userComment) {
        userCommentRepository.save(userComment);
    }
}

