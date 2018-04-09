package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.LabelUI;
import java.util.LinkedList;
import java.util.List;

@Service
public class FriendManager implements IFriendManager {

    private FriendRepository friendRepository;

    @Autowired
    public FriendManager(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public List<Friend> getFriendsList(Long id) {
        return friendRepository.getFriendsByUserId(id);
    }

    public List<User> getUsersFromFriendsList(Long id){
        List<Friend> friends = getFriendsList(id);
        List<User> users = new LinkedList<>();
        for(Friend friend : friends){
            users.add(friend.getFriend());
        }

        return users;
    }

    public void addFriend(Long loggedUserid, User user){
        Friend friend = new Friend();
        friend.setUserId(loggedUserid);
        friend.setFriend(user);
        friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Long loggedUserid, User user) {
        Friend friend = friendRepository.getFriendByUserIdAndFriend(loggedUserid, user);
        friendRepository.delete(friend);
    }
}