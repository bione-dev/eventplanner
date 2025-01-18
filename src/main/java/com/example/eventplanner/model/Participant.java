package com.example.eventplanner.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Participant extends PanacheEntity {
    public String name;
    public String email;
}
