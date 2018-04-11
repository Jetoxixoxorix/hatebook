package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FriendManager implements IFriendManager {

    private FriendRepository friendRepository;
    private InvitationManager invitationManager;
    private IUserManager userManager;

    @Autowired
    public FriendManager(FriendRepository friendRepository, InvitationManager invitationManager, IUserManager userManager) {
        this.friendRepository = friendRepository;
        this.invitationManager = invitationManager;
        this.userManager = userManager;
    }

    public List<Friend> getFriendsList(Long id) {
        return friendRepository.getFriendsByUserId(id);
    }

    public List<User> getUsersFromFriendsList(Long id) {
        List<Friend> friends = getFriendsList(id);
        List<User> users = new LinkedList<>();
        for (Friend friend : friends) {
            users.add(friend.getFriend());
        }

        return users;
    }

    public void addFriend(Long loggedUserid, User user, Long invitationId) {
        Friend friend = new Friend();
        friend.setUserId(loggedUserid);
        friend.setFriend(user);
        friendRepository.save(friend);

        Friend friend2 = new Friend();
        friend2.setUserId(user.getId());
        friend2.setFriend(userManager.getUserById(loggedUserid));
        friendRepository.save(friend2);

        invitationManager.deleteInvitation(invitationId);
    }

    @Override
    public void deleteFriend(Long loggedUserid, User user) {
        Friend friend = friendRepository.getFriendByUserIdAndFriend(loggedUserid, user);
        friendRepository.delete(friend);
    }
}
