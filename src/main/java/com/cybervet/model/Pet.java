package com.cybervet.model;

import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.PhysiologicalState;
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
    private PhysiologicalState physiologicalState;
    @ManyToOne
    private AppUser owner;
    @ManyToOne(cascade = CascadeType.ALL)
    private Diet diet;
    private LocalDateTime createdAt;

    public Pet() {}

    public Pet(PetDto petDto) {
        this.name = petDto.getName();
        this.breed = petDto.getBreed();
        this.gender = petDto.getGender();
        this.type = petDto.getType();
        this.condition = petDto.getCondition();
        this.age = petDto.getAge();
        this.weight = petDto.getWeight();
        this.activityLevel = petDto.getActivityLevel();
        this.energySource = petDto.getEnergySource();
        this.physiologicalState = petDto.getPhysiologicalState();
        this.createdAt = LocalDateTime.now();
    }

}


