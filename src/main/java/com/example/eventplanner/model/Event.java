package com.example.eventplanner.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Event name must not be blank")
    public String name;

    @NotNull(message = "Event date must not be null")
    public LocalDateTime date;

    @NotBlank(message = "Event location must not be blank")
    public String location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Participant> participants;
}
