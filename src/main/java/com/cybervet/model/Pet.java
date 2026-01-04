package com.cybervet.model;

import com.cybervet.model.dto.PetDto;
import com.cybervet.model.enums.*;
import jakarta.persistence.*;
import lombok.Data;

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
    private TypeOfAnimal typeOfAnimal;
    private Condition condition;
    private PetAge age;
    private double weight;
    private ActivityLevel activityLevel;
    private String energySource;
    private PhysiologicalState physiologicalState;
    @ManyToOne
    private AppUser owner;
    @ManyToOne(cascade = CascadeType.ALL)
    private Diet diet = null;
    private LocalDateTime createdAt;

    public Pet() {}

    public Pet(PetDto petDto) {
        this.name = petDto.getName();
        this.breed = petDto.getBreed();
        this.gender = petDto.getGender();
        this.typeOfAnimal = petDto.getTypeOfAnimal();
        this.condition = petDto.getCondition();
        this.age = petDto.getAge();
        this.weight = petDto.getWeight();
        this.activityLevel = petDto.getActivityLevel();
        this.energySource = petDto.getEnergySource();
        this.physiologicalState = petDto.getPhysiologicalState();
        this.createdAt = LocalDateTime.now();
    }

}


