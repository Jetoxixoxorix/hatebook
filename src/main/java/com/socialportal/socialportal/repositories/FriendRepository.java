package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> getFriendsByUserId(Long id);
    Friend getFriendByUserIdAndFriend(Long id, User friend);
}
