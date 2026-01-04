package com.cybervet.model.dto;

import com.cybervet.model.enums.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDto {
    private String name;
    private String breed;
    private String gender;
    private TypeOfAnimal typeOfAnimal;
    private Condition condition;
    private PetAge age;
    private double weight;
    private ActivityLevel activityLevel;
    private String energySource;
    private long chatId;
    private PhysiologicalState physiologicalState;
}
