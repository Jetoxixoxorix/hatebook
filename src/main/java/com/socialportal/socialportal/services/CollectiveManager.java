package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.models.CollectiveMember;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.CollectiveMemberRepository;
import com.socialportal.socialportal.repositories.CollectiveRepository;
import javafx.scene.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CollectiveManager {

    private CollectiveRepository collectiveRepository;
    private CollectiveMemberRepository collectiveMemberRepository;

    @Autowired
    public CollectiveManager(CollectiveRepository collectiveRepository, CollectiveMemberRepository collectiveMemberRepository) {
        this.collectiveRepository = collectiveRepository;
        this.collectiveMemberRepository = collectiveMemberRepository;
    }

    public void createGroup(Collective group, User user){
        group.setCreatingDate(new Date());
        collectiveRepository.save(group);

        CollectiveMember collectiveMember = new CollectiveMember();
        collectiveMember.setGroup(group);
        collectiveMember.setUser(user);
        collectiveMember.setAdmin(true);

        collectiveMemberRepository.save(collectiveMember);
    }


    public List<Collective> getGroups(User user) {
        List<CollectiveMember> members = collectiveMemberRepository.getCollectiveMemberByUser(user);
        List<Collective> groups = new LinkedList<>();
        for (CollectiveMember member: members) {
            groups.add(member.getGroup());
        }
        
        return groups;
    }
}
