package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ExerciseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int exercise_Api_Id;

    @Column(nullable = false)
    private int reps;
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

    public ExerciseDetails(Long id,int exercise_Api_Id, int reps, int sets, Exercise exercise, Workout workout) {
        this.id = id;
        this.exercise_Api_Id = exercise_Api_Id;
        this.reps = reps;
        this.sets = sets;
        this.exercise = exercise;
        this.workout = workout;
    }

    public ExerciseDetails(int reps,int exercise_Api_Id, int sets, Exercise exercise, Workout workout) {
        this.exercise_Api_Id = exercise_Api_Id;
        this.reps = reps;
        this.sets = sets;
        this.exercise = exercise;
        this.workout = workout;
    }

    public ExerciseDetails(int exercise_Api_Id, int reps, int sets) {
        this.exercise_Api_Id = exercise_Api_Id;
        this.reps = reps;
        this.sets = sets;
    }

    public int getExercise_Api_Id() {
        return exercise_Api_Id;
    }

    public void setExercise_Api_Id(int exercise_Api_Id) {
        this.exercise_Api_Id = exercise_Api_Id;
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