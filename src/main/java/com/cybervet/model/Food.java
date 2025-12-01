package com.cybervet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Food {
    @Id
    private int id;
    private String description;
    @OneToOne
    private NutritionalValue nutritionalValue;
    private int standartPortion;
}
