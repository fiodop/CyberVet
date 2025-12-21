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
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pet> petList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Feedback> feedbackList = new ArrayList<>();

    public AppUser(String username, long telegramId, long chatId, List<Pet> petList, List<Feedback> feedbackList) {
        this.username = username;
        this.telegramId = telegramId;
        this.chatId = chatId;
        this.petList = petList;
        this.feedbackList = feedbackList;
    }

    public AppUser() {

    }
}
