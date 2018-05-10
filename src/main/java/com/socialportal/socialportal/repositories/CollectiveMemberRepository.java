package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Collective;
import com.socialportal.socialportal.models.CollectiveMember;
import com.socialportal.socialportal.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectiveMemberRepository extends CrudRepository<CollectiveMember, Long> {
    List getCollectiveMemberByUser(User user);
    CollectiveMember getCollectiveMemberByUserAndGroup(User user, Collective group);
    List<CollectiveMember> getCollectiveMembersByGroup(Collective group);
}
