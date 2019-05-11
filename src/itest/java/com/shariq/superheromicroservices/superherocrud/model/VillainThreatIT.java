package com.shariq.superheromicroservices.superherocrud.model;

import com.shariq.superheromicroservices.superherocrud.repository.HeroRepo;
import com.shariq.superheromicroservices.superherocrud.repository.ThreatRepo;
import com.shariq.superheromicroservices.superherocrud.repository.VillainRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class VillainThreatIT {

    @Autowired
    private VillainRepo villainRepo;

    @Autowired
    private ThreatRepo threatRepo;

    @Autowired
    private HeroRepo heroRepo;

    @Before
    public void setup() {
        threatRepo.deleteAll();
        villainRepo.deleteAll();
        heroRepo.deleteAll();
    }

    @Test
    public void villainSavesThreats() {
        Villain newVillain = new Villain("villain1");

        Threat threat1 = new Threat("threat1", newVillain);
        Threat threat2 = new Threat("threat2", newVillain);
        List<Threat> threatList = new ArrayList<>();
        threatList.add(threat1);
        threatList.add(threat2);

        newVillain.setThreats(threatList);

        Villain updatedVillain = villainRepo.save(newVillain);
        Villain savedThreatVillainAssociation = threatRepo.findThreatByName("threat1").getVillain();

        assertThat("updatedVillain did not have correct number of threats", updatedVillain.getThreats(), hasSize(2));
        assertThat("saved threats did not have bi-directional villain association", savedThreatVillainAssociation.getName(), equalTo("villain1"));
    }

    @Test
    public void threatGetsHeroWhenHeroAssignedThreat() {
        Villain newVillain = new Villain("villain");

        Threat threat1 = new Threat("threat", newVillain);
        List<Threat> threatList = new ArrayList<>();
        threatList.add(threat1);

        newVillain.setThreats(threatList);
        villainRepo.save(newVillain);

        Threat currentThreat = threatRepo.findThreatByName("threat");

        Hero newHero = new Hero("hero");
        newHero.setThreat(currentThreat);
        heroRepo.save(newHero);

        Hero currentHero = heroRepo.findHeroByName("hero");

        assertThat("threat did not have a hero assigned to it", currentHero.getThreat().getName(), equalTo(currentThreat.getName()));
    }
}
