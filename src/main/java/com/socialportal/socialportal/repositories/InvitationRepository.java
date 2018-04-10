package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Invitation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
    Invitation getInvitationById(Long id);
    List<Invitation> getInvitationsByUserId(Long id);
}
