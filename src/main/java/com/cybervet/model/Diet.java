package com.cybervet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Food food;
    @OneToOne
    private Notification notification;

}
