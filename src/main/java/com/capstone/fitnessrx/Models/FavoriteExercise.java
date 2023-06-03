package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="favorite_exercise")
public class FavoriteExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_exercise_exercise",
            joinColumns = {@JoinColumn(name = "favorite_exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")}
    )
    private List<Exercise> exercises;


    public FavoriteExercise() {
    }

    public FavoriteExercise(long id, User user, List<Exercise> exercises) {
        this.id = id;
        this.user = user;
        this.exercises = exercises;
    }

    public FavoriteExercise(User user, List<Exercise> exercises) {
        this.user = user;
        this.exercises = exercises;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
