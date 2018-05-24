package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.models.CollectiveMember;
import com.socialportal.socialportal.models.User;

import java.util.List;

public interface ICollectiveManager {
    Collective getGroup(Long id);

    List<Collective> getGroups(User user);

    List<CollectiveMember> getGroupMembers(Collective group);

    boolean isMemberOfGroup(User user, Collective group);

    void createGroup(Collective group, User user);

    void addMember(Collective group, User user);

    void removeFromGroup(Collective group, User user);

    boolean isAdmin(Collective group, User user);

    void makeUserAnAdmin(Collective group, User user);

    void removeAdminFromUser(Collective group, User user);
}
