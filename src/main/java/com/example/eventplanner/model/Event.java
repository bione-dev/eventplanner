package com.example.eventplanner.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática de IDs
    public Long id;

    public String name;
    public LocalDateTime date;
    public String location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Participant> participants;
}
