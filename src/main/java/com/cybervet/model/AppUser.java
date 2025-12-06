package com.cybervet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private long telegramId;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Pet> petList;

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Feedback> feedbackList;
}
