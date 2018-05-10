package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserById(Long id);

    List<User> findUserByFirstNameOrLastName(String name, String lastName);
}
