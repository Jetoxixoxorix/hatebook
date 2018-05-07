package com.socialportal.socialportal.repositories;

import com.socialportal.socialportal.models.Collective;
import org.springframework.data.repository.CrudRepository;

public interface CollectiveRepository extends CrudRepository<Collective, Long> {
    Collective getCollectiveById(Long id);
}
