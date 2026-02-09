package com.cybervet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "username",
            length = 100,
            nullable = false
    )
    private String username;

    @Column(
            name = "telegram_id",
            nullable = false,
            unique = true
    )
    private Long telegramId;

    @Column(
            name = "chat_id",
            nullable = false
    )
    private Long chatId;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Pet> petList = new ArrayList<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Feedback> feedbackList = new ArrayList<>();

    public User(String username, long telegramId, long chatId, List<Pet> petList, List<Feedback> feedbackList) {
        this.username = username;
        this.telegramId = telegramId;
        this.chatId = chatId;
        this.petList = petList;
        this.feedbackList = feedbackList;
    }

    public User() {

    }
}
