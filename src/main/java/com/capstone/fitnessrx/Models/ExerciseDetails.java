package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExerciseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int reps;
    @Column(nullable = false)
    private String weight;
    @Column(nullable = false)
    private int sets;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;


    public ExerciseDetails() {
    }

    public ExerciseDetails(Long id, int reps, String weight, int sets, Exercise exercise, Workout workout) {
        this.id = id;
        this.reps = reps;
        this.weight = weight;
        this.sets = sets;
        this.exercise = exercise;
        this.workout = workout;
    }

    public ExerciseDetails(int reps, String weight, int sets, Exercise exercise, Workout workout) {
        this.reps = reps;
        this.weight = weight;
        this.sets = sets;
        this.exercise = exercise;
        this.workout = workout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}