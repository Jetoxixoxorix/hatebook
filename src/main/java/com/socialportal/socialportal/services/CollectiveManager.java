package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.models.CollectiveMember;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.CollectiveMemberRepository;
import com.socialportal.socialportal.repositories.CollectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CollectiveManager implements ICollectiveManager {

    private CollectiveRepository collectiveRepository;
    private CollectiveMemberRepository collectiveMemberRepository;

    @Autowired
    public CollectiveManager(CollectiveRepository collectiveRepository, CollectiveMemberRepository collectiveMemberRepository) {
        this.collectiveRepository = collectiveRepository;
        this.collectiveMemberRepository = collectiveMemberRepository;
    }

    @Override
    public Collective getGroup(Long id) {
        return collectiveRepository.getCollectiveById(id);
    }

    @Override
    public List<Collective> getGroups(User user) {
        List<CollectiveMember> members = collectiveMemberRepository.getCollectiveMemberByUser(user);
        List<Collective> groups = new LinkedList<>();
        for (CollectiveMember member : members) {
            groups.add(member.getGroup());
        }

        return groups;
    }

    @Override
    public List<CollectiveMember> getGroupMembers(Collective group) {
        return collectiveMemberRepository.getCollectiveMembersByGroup(group);
    }

    @Override
    public boolean isMemberOfGroup(User user, Collective group) {
        return collectiveMemberRepository.getCollectiveMemberByUserAndGroup(user, group) != null;
    }

    @Override
    public void createGroup(Collective group, User user) {
        group.setCreatingDate(new Date());
        collectiveRepository.save(group);

        CollectiveMember collectiveMember = new CollectiveMember();
        collectiveMember.setGroup(group);
        collectiveMember.setUser(user);
        collectiveMember.setAdmin(true);

        collectiveMemberRepository.save(collectiveMember);
    }

    @Override
    public void addMember(Collective group, User user) {
        CollectiveMember collectiveMember = new CollectiveMember();
        collectiveMember.setGroup(group);
        collectiveMember.setUser(user);

        collectiveMemberRepository.save(collectiveMember);
    }

    @Override
    public void removeFromGroup(Collective group, User user) {
        CollectiveMember collectiveMember = collectiveMemberRepository.getCollectiveMemberByUserAndGroup(user, group);
        collectiveMemberRepository.delete(collectiveMember);
    }

    @Override
    public boolean isAdmin(Collective group, User user) {
        CollectiveMember collectiveMember = collectiveMemberRepository.getCollectiveMemberByUserAndGroup(user, group);
        if (collectiveMember == null)
            return false;
        return collectiveMember.isAdmin();
    }

    @Override
    public void makeUserAnAdmin(Collective group, User user) {
        CollectiveMember member = collectiveMemberRepository.getCollectiveMemberByUserAndGroup(user, group);
        member.setAdmin(true);
        collectiveMemberRepository.save(member);
    }

    @Override
    public void removeAdminFromUser(Collective group, User user) {
        CollectiveMember member = collectiveMemberRepository.getCollectiveMemberByUserAndGroup(user, group);
        member.setAdmin(false);
        collectiveMemberRepository.save(member);
    }
}
