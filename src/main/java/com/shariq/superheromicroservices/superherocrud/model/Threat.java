package com.shariq.superheromicroservices.superherocrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Threat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String name;

    @OneToOne(mappedBy = "threat")
    private Hero hero;

    @ManyToOne
    @JoinColumn(name = "villain_id")
    private Villain villain;

    public Threat(String name, Villain villain) {
        this.name = name;
        this.villain = villain;
    }
}
