package com.cybervet.model.dto;

import com.cybervet.model.enums.ActivityLevel;
import com.cybervet.model.enums.Condition;
import com.cybervet.model.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetDto {
    private String name;
    private String breed;
    private String gender;
    private Type type;
    private Condition condition;
    private int age;
    private double weight;
    private ActivityLevel activityLevel;
    private String energySource;
}
