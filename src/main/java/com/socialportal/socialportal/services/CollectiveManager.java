package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.repositories.CollectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CollectiveManager {

    private CollectiveRepository collectiveRepository;

    @Autowired
    public CollectiveManager(CollectiveRepository collectiveRepository) {
        this.collectiveRepository = collectiveRepository;
    }

    public void createGroup(Collective group){
        group.setCreatingDate(new Date());
        collectiveRepository.save(group);
    }


}
