package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
public class Calender {



    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Column(nullable = false)
    private int day_id;

    public Calender() {
    }

    public Calender(Long id, User user, int day_id) {
        this.id = id;
        this.user = user;
        this.day_id = day_id;
    }

    public Calender(User user, int day_id) {
        this.user = user;
        this.day_id = day_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }
}
