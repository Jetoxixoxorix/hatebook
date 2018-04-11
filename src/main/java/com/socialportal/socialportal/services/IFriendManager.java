package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;

import java.util.List;

public interface IFriendManager {
    List<Friend> getFriendsOfUser(Long id);
    List<User> getUsersFromFriendsOfUser(Long id);

    void addFriend(Long loggedUserid, User user, Long invitationId);
    void deleteFriend(Long loggedUserid, User user);
}
