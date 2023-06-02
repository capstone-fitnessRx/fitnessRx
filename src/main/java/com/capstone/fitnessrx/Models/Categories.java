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

    @ManyToMany(mappedBy = "categories")
    private List<Workout> workout_id;

    @ManyToMany(mappedBy = "categories")
    private List<Exercise> exercise_id;
}
