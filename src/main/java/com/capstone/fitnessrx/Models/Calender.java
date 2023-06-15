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
    private int dayId;
    @Column()
    private String notes;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name = "workoutId")
    private Workout workout;

    public Calender(Long id, User user, int dayId, String notes, Workout workout) {
        this.id = id;
        this.user = user;
        this.dayId = dayId;
        this.notes = notes;
        this.workout = workout;
    }

    public Calender(User user, int dayId, String notes, Workout workout) {
        this.user = user;
        this.dayId = dayId;
        this.notes = notes;
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Calender() {
    }

    public Calender( User user, int dayId, Workout workout) {
        this.user = user;
        this.dayId = dayId;
        this.workout = workout;
    }

    public Calender(User user, int dayId, String notes) {
        this.user = user;
        this.dayId = dayId;
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

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

