package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.repositories.UserCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentManager implements ICommentManager {

    private UserCommentRepository userCommentRepository;

    @Autowired
    public CommentManager(UserCommentRepository userCommentRepository){
        this.userCommentRepository = userCommentRepository;
    }

    public List<UserComment> getUserComments(Long id) {
        return userCommentRepository.getUserCommentsByUserId(id);
    }

    public void addNewComment(UserComment userComment, Long userProfileId, UserStatus userStatus, User user) {
        userComment.setUserId(userProfileId);
        userComment.setDate(new Date());
        userComment.setUserStatus(userStatus);
        userComment.setAddingUser(user);
        userCommentRepository.save(userComment);
    }

    public void deleteComment(Long id){
        userCommentRepository.delete(userCommentRepository.getUserCommentsByCommentId(id));
    }

    //temporary
    public Iterable<UserComment> allComments() {
        return userCommentRepository.findAll();
    }
}

