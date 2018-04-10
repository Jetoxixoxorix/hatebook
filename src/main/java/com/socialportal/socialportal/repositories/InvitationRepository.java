package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Invitation;
import org.springframework.data.repository.CrudRepository;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
    Invitation getInvitationById(Long id);
}
