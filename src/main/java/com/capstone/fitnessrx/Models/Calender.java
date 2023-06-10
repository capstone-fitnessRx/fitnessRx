package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int day_id;
    @Column()
    private String notes;

    public Calender() {
    }

    public Calender(Long id, User user, int day_id, String notes) {
        this.id = id;
        this.user = user;
        this.day_id = day_id;
        this.notes = notes;
    }

    public Calender(User user, int day_id, String notes) {
        this.user = user;
        this.day_id = day_id;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

