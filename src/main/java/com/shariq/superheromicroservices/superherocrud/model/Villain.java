package com.shariq.superheromicroservices.superherocrud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Villain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="villain_id")
    private Long Id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "villain", cascade = CascadeType.PERSIST)
    private List<Threat> threats;

    public Villain(String name) {
        this.name = name;
    }
}
