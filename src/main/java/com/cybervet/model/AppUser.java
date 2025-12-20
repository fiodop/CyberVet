package com.cybervet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private long telegramId;
    private long chatId;
    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Pet> petList = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbackList = new ArrayList<>();

    public AppUser(String username, long telegramId, long chatId) {}

    public AppUser() {

    }
}
