package com.cybervet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Diet {
    private int id;
    @OneToOne
    private Food food;
    @OneToOne
    private Notification notification;

}
