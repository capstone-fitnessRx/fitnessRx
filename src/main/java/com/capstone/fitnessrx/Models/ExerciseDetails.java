package com.capstone.fitnessrx.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ExerciseDetails {
    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private int reps;
    @Column(nullable = false)
    private String weight;
    @Column(nullable = false)
    private int sets;

    public ExerciseDetails() {
    }

    public ExerciseDetails(Long id, int reps, String weight, int sets) {
        this.id = id;
        this.reps = reps;
        this.weight = weight;
        this.sets = sets;
    }

    public ExerciseDetails(int reps, String weight, int sets) {
        this.reps = reps;
        this.weight = weight;
        this.sets = sets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
}
