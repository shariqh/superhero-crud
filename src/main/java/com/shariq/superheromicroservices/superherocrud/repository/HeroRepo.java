package com.shariq.superheromicroservices.superherocrud.repository;

import com.shariq.superheromicroservices.superherocrud.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HeroRepo extends JpaRepository<Hero, Long> {

    Hero findHeroByName(String name);

}
