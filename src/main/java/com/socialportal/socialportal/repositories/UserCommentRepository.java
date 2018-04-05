package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.UserComment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCommentRepository extends CrudRepository<UserComment, Long> {
    List<UserComment> getUserCommentsByUserId(Long id);
}
