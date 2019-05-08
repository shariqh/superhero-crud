package com.shariq.superheromicroservices.superherocrud.config;

import com.shariq.superheromicroservices.superherocrud.model.Hero;
import com.shariq.superheromicroservices.superherocrud.model.Threat;
import com.shariq.superheromicroservices.superherocrud.model.Villain;
import com.shariq.superheromicroservices.superherocrud.repository.HeroRepo;
import com.shariq.superheromicroservices.superherocrud.repository.ThreatRepo;
import com.shariq.superheromicroservices.superherocrud.repository.VillainRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ThreatRepo threatRepo;

    @Autowired
    private VillainRepo villainRepo;

    @Autowired
    private HeroRepo heroRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        //
//        heroRepo.save(new Hero("Wonder Woman"));
//
//        Threat threat1 = threatRepo.save(new Threat("Earthquake"));
//        Threat threat2 = threatRepo.save(new Threat("Flood"));
//
//        villainRepo.save(new Villain("Joker"));
//
//        Hero hero1 = heroRepo.findHeroByName("Wonder Woman");
//        hero1.setThreat(threatRepo.findThreatByName("Earthquake"));
//        heroRepo.save(hero1);
//
//        Villain villain1 = villainRepo.findVillainByName("Joker");
//        List<Threat> threatList = new ArrayList<>();
//        threatList.add(threat1);
//        threatList.add(threat2);
//        villain1.setThreats(threatList);
//        villainRepo.save(villain1);

        //now lets see what's actually in there
        threatRepo.findAll().stream().forEach(threat -> {
            System.out.println(threat.getId() + " " + threat.getVillain().getId());
        });
    }
}
