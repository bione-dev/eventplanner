package com.example.eventplanner.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Participant extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Name must not be blank")
    public String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email must not be blank")
    public String email;

    public Participant() {
    }

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
