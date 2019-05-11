package com.shariq.superheromicroservices.superherocrud.config;

import com.shariq.superheromicroservices.superherocrud.model.Hero;
import com.shariq.superheromicroservices.superherocrud.model.Threat;
import com.shariq.superheromicroservices.superherocrud.model.Villain;
import com.shariq.superheromicroservices.superherocrud.repository.HeroRepo;
import com.shariq.superheromicroservices.superherocrud.repository.ThreatRepo;
import com.shariq.superheromicroservices.superherocrud.repository.VillainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("local")
public class InitialLoader {

    final private HeroRepo heroRepo;
    final private ThreatRepo threatRepo;
    final private VillainRepo villainRepo;

    @Autowired
    public InitialLoader(HeroRepo heroRepo, ThreatRepo threatRepo, VillainRepo villainRepo) {
        this.heroRepo = heroRepo;
        this.threatRepo = threatRepo;
        this.villainRepo = villainRepo;
    }

    @PostConstruct
    private void createRelationships() {
        Hero newHero = new Hero("Wonder Woman");

        Villain newVillain = new Villain("Joker");

        Threat threat1 = new Threat("Earthquake", newVillain);
        Threat threat2 = new Threat("Flood", newVillain);
        List<Threat> threatList = new ArrayList<>();
        threatList.add(threat1);
        threatList.add(threat2);

        newVillain.setThreats(threatList);

        villainRepo.save(newVillain);

        newHero.setThreat(threatRepo.findThreatByName("Earthquake"));

        heroRepo.save(newHero);

    }

}
