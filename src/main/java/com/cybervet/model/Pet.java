package com.cybervet.model;

import jakarta.persistence.*;
import lombok.Data;
import com.cybervet.model.enums.ActivityLevel;
import com.cybervet.model.enums.Condition;
import com.cybervet.model.enums.Type;

import java.time.LocalDateTime;

@Entity
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String breed;
    private String gender;
    private Type type;
    private Condition condition;
    private int age;
    private double weight;
    private ActivityLevel activityLevel;
    private String energySource;

    @ManyToOne(cascade = CascadeType.ALL)
    private Diet diet;
    private LocalDateTime createdAt;
}
