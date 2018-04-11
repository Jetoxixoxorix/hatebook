package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCommentRepository extends CrudRepository<UserComment, Long> {
    List<UserComment> getUserCommentsByUserId(Long id);
    UserComment getUserCommentByCommentId(Long id);

    List<UserComment> getCommentsByUserStatus(UserStatus userStatus);
}
