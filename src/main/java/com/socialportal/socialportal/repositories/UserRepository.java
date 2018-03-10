package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
}
