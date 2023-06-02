package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String category_name;

    @ManyToMany(mappedBy = "title")
    private List<Workout> workout_id;

    @ManyToMany(mappedBy = "exercise_name")
    private List<Exercise> exercise_id;

    public Categories() {
    }

    public Categories(long id, String category_name, List<Workout> workout_id, List<Exercise> exercise_id) {
        this.id = id;
        this.category_name = category_name;
        this.workout_id = workout_id;
        this.exercise_id = exercise_id;
    }

    public Categories(String category_name, List<Workout> workout_id, List<Exercise> exercise_id) {
        this.category_name = category_name;
        this.workout_id = workout_id;
        this.exercise_id = exercise_id;
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

    public List<Workout> getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(List<Workout> workout_id) {
        this.workout_id = workout_id;
    }

    public List<Exercise> getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(List<Exercise> exercise_id) {
        this.exercise_id = exercise_id;
    }
}
