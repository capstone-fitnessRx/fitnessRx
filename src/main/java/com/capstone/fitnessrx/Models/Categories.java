package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String category_name;

    @ManyToMany(mappedBy = "categories")
    private List<Workout> workouts;

    @ManyToMany(mappedBy = "categories")
    private List<Exercise> exercises;

    public Categories() {
    }

    public Categories(long id, String category_name, List<Workout> workouts, List<Exercise> exercises) {
        this.id = id;
        this.category_name = category_name;
        this.workouts = workouts;
        this.exercises = exercises;
    }

    public Categories(String category_name, List<Workout> workouts, List<Exercise> exercises) {
        this.category_name = category_name;
        this.workouts = workouts;
        this.exercises = exercises;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}