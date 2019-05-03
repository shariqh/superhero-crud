package com.shariq.superheromicroservices.superherocrud.repository;

import com.shariq.superheromicroservices.superherocrud.model.Villain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VillainRepo extends JpaRepository<Villain, Long> {
}
