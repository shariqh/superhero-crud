package com.shariq.superheromicroservices.superherocrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String name;

    @OneToOne
    @JoinTable(name = "hero_threat",
            joinColumns = { @JoinColumn(name = "hero_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "threat_id", referencedColumnName = "id") })
    private Threat threat;

    public Hero(String name) {
        this.name = name;
    }
}
