package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
public class Ratings {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Wo_id")
    private Workout workout;


    public Ratings() {
    }

    public Ratings(int rating, User user, Workout workout) {
        this.rating = rating;
        this.user = user;
        this.workout = workout;
    }

    public Ratings(Long id, int rating, User user, Workout workout) {
        this.id = id;
        this.rating = rating;
        this.user = user;
        this.workout = workout;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
