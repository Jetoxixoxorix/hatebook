package com.socialportal.socialportal.services;

import com.socialportal.socialportal.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupManager {

    private GroupRepository groupRepository;

    @Autowired
    public GroupManager(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void createGroup(){

    }


}
