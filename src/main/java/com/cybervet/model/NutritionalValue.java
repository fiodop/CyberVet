package com.cybervet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class NutritionalValue {
    @Id
    private int id;
    private double protein;
    private double carbs;
    private double sugar;
    private double fats;
    private int calories;
}
