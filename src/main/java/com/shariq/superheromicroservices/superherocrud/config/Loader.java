package com.shariq.superheromicroservices.superherocrud.config;

import com.shariq.superheromicroservices.superherocrud.repository.ThreatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Loader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ThreatRepo threatRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //now lets see what's actually in there
        threatRepo.findAll().stream().forEach(threat -> {
            System.out.println(threat.getId() + " " + threat.getVillain().getId());
        });
    }
}
