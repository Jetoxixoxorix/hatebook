package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.UserStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserStatusRepository extends CrudRepository<UserStatus, Long> {
    List<UserStatus> getUserStatusesByUserIdOrderByDateDesc(Long id);
    UserStatus getUserStatusByUserId(Long id);
}
