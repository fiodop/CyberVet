package com.cybervet.model;

import com.cybervet.model.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

@Data
@Entity
@NoArgsConstructor
public class FoodExel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name",
            length = 500,
            nullable = false
    )
    private String name;

    @Lob
    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type_of_animal",
            nullable = false,
            length = 50
    )
    private TypeOfAnimal typeOfAnimal;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "breed",
            nullable = false,
            length = 50
    )
    private Breed breed;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "age",
            nullable = false,
            length = 30
    )
    private PetAge age;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "physiological_state",
            nullable = false,
            length = 50
    )
    private PhysiologicalState physiologicalState;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "activity_level",
            nullable = false,
            length = 30
    )
    private ActivityLevel activityLevel;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type_of_food",
            nullable = false,
            length = 50
    )
    private TypeOfFood typeOfFood;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "price_segment",
            nullable = false,
            length = 30
    )
    private PriceSegment priceSegment;

    @Column(
            name = "link",
            length = 1000
    )
    private String link;

    private static final DataFormatter FORMATTER = new DataFormatter();

    public FoodExel(Row row) {

        String animal = FORMATTER.formatCellValue(row.getCell(0)).trim();
        switch (animal) {
            case "Кошка" -> this.typeOfAnimal = TypeOfAnimal.CAT;
            case "Собака" -> this.typeOfAnimal = TypeOfAnimal.DOG;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный вид животных: " + animal + "."
            );
        }

        String breedDto = FORMATTER.formatCellValue(row.getCell(1)).trim();
        switch (breedDto) {
            case "Крупная" -> this.breed = Breed.BIG;
            case "Средняя" -> this.breed = Breed.AVERAGE;
            case "Мелкая" -> this.breed = Breed.SMALL;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный вид породы: " + breedDto + "."
            );
        }

        String ageString = FORMATTER.formatCellValue(row.getCell(2)).trim();
        switch (ageString) {
            case "до 2 месцев" -> this.age = PetAge.BEFORE_2_MONTHS;
            case "до 4 месцев" -> this.age = PetAge.BEFORE_4_MONTHS;
            case "до 1 года" -> this.age = PetAge.BEFORE_1_YEAR;
            case "до 5 лет" -> this.age = PetAge.BEFORE_5_YEARS;
            case "до 7 лет" -> this.age = PetAge.BEFORE_7_YEARS;
            case "до 12 лет" -> this.age = PetAge.BEFORE_12_YEARS;
            case "более 5 лет" -> this.age = PetAge.MORE_THAN_5_YEARS;
            case "более 12 лет" -> this.age = PetAge.MORE_THAN_12_YEARS;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный вид возраста животного: " + ageString + "."
            );
        }

        String stateString = FORMATTER.formatCellValue(row.getCell(3)).trim();
        switch (stateString) {
            case "Стерилизованный/кастрированный питомец" ->
                    this.physiologicalState = PhysiologicalState.CASTRATED;
            case "Не стерилизованный и не беременный питомец" ->
                    this.physiologicalState = PhysiologicalState.NORMAL_STATE;
            case "Беременный/кормящий питомец" ->
                    this.physiologicalState = PhysiologicalState.PREGNANT;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данное физиологическое состояние: " + stateString + "."
            );
        }

        String activityString = FORMATTER.formatCellValue(row.getCell(4)).trim();
        switch (activityString) {
            case "Высокоактивный" -> this.activityLevel = ActivityLevel.HIGH;
            case "Среднеактивный" -> this.activityLevel = ActivityLevel.MEDIUM;
            case "Малоактивные" -> this.activityLevel = ActivityLevel.LOW;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный вид активности: " + activityString + "."
            );
        }

        String typeOfFoodDto = FORMATTER.formatCellValue(row.getCell(5)).trim();
        switch (typeOfFoodDto) {
            case "Курица" -> this.typeOfFood = TypeOfFood.CHICKEN;
            case "Рыба" -> this.typeOfFood = TypeOfFood.FISH;
            case "Красное мясо" -> this.typeOfFood = TypeOfFood.BEEF;
            case "Нет предпочтений" -> this.typeOfFood = TypeOfFood.NO_PREFERENCES;
            case "Индейка" -> this.typeOfFood = TypeOfFood.TURKEY;
            case "Смешанный" -> this.typeOfFood = TypeOfFood.MIXED;
            case "Утка" -> this.typeOfFood = TypeOfFood.DUCK;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный вид мяса: " + typeOfFoodDto + "."
            );
        }

        String priceSegmentStr = FORMATTER.formatCellValue(row.getCell(6)).trim();
        switch (priceSegmentStr) {
            case "Эконом" -> this.priceSegment = PriceSegment.ECONOMY;
            case "Эконом " -> this.priceSegment = PriceSegment.ECONOMY_;
            case "Суперпремиум" -> this.priceSegment = PriceSegment.SUPER_PREMIUM;
            case "Холистик" -> this.priceSegment = PriceSegment.HOLISTIC;
            default -> throw new IllegalArgumentException(
                    "Не поддерживается данный ценовой сегмент: " + priceSegmentStr + "."
            );
        }

        this.name = FORMATTER.formatCellValue(row.getCell(7)).trim();
        this.description = FORMATTER.formatCellValue(row.getCell(8)).trim();
        this.link = FORMATTER.formatCellValue(row.getCell(9)).trim();
    }
}
