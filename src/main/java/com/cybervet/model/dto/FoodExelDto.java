package com.cybervet.model.dto;

import com.cybervet.model.enums.*;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

@Data
public class FoodExelDto {
    private String name;
    private String description;
    private TypeOfAnimal typeOfAnimal;
    private String breed;
    private PetAge age;
    private PhysiologicalState physiologicalState;
    private ActivityLevel activityLevel;
    private String typeOfFood;
    private PriceSegment priceSegment;


    public FoodExelDto(Row row) {
        if(row.getCell(0).getStringCellValue().equals("Кошка")){
            this.typeOfAnimal = TypeOfAnimal.CAT;
        } else if (row.getCell(0).getStringCellValue().equals("Собака")){
            this.typeOfAnimal = TypeOfAnimal.DOG;
        } else throw new IllegalArgumentException("Не поддерживается данный вид животных: " + row.getCell(0).getStringCellValue());

        this.breed = row.getCell(1).getStringCellValue();

        String ageString = row.getCell(2).getStringCellValue();
        switch (ageString) {
            case "До 2 месяцев" -> this.age = PetAge.BEFORE_2_MONTHS;
            case "До года" -> this.age = PetAge.BEFORE_1_YEAR;
            case "До 5 лет" -> this.age = PetAge.BEFORE_5_YEARS;
            case "Старше 5 лет" -> this.age = PetAge.OLDER_5_YEARS;
            default -> throw new IllegalArgumentException("Не поддержиавется данный вид возраста животного: " + ageString);
        }

        String stateString = row.getCell(3).getStringCellValue();
        switch (stateString) {
            case "Стерилизованный/кастрированный питомец" -> this.physiologicalState = PhysiologicalState.CASTRATED;
            case "Нормальное состояние" -> this.physiologicalState = PhysiologicalState.NORMAL_STATE;
            case "Беременный питомец" -> this.physiologicalState = PhysiologicalState.PREGNANT;
        }

        String activityString = row.getCell(4).getStringCellValue();
        switch (activityString) {
            case "Высокоактивный" -> this.activityLevel = ActivityLevel.HIGH;
            case "Среднеактивный" -> this.activityLevel = ActivityLevel.MEDIUM;
            case "Малоактивный" -> this.activityLevel = ActivityLevel.LOW;
            default -> throw new IllegalArgumentException("Не поддерживается данный вид активности: " + activityString);
        }

        this.typeOfFood = row.getCell(5).getStringCellValue();

        String priceSegment = row.getCell(6).getStringCellValue();
        switch (priceSegment) {
            case "Эконом" -> this.priceSegment = PriceSegment.ECONOMY;
            case "Суперпремиум" -> this.priceSegment = PriceSegment.SUPER_PREMIUM;
            case "Холистик" -> this.priceSegment = PriceSegment.HOLISTIC;
            default -> throw new IllegalArgumentException("Не поддерживается данный вид корма: " + priceSegment);
        }

        this.name = row.getCell(7).getStringCellValue();
        this.description = row.getCell(8).getStringCellValue();

    }
}
