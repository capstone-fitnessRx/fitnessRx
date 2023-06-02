package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String exercise_name;

    @ManyToOne
    @JoinColumn(name = "ex_id")
    private Exercise exercise;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ex_cat",
            joinColumns = {@JoinColumn(name = "ex_id")},
            inverseJoinColumns = {@JoinColumn(name = "cat_id")}
    )
    private List<Categories> categories;
    @Column(nullable = false, length = 100)
    private String target_muscle;
    @Column(nullable = false, length = 100)
    private String equipment;
    @Column(nullable = false, length = 100)
    private String bodypart;

    public String getTarget_muscle() {
        return target_muscle;
    }

    public void setTarget_muscle(String target_muscle) {
        this.target_muscle = target_muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getBodypart() {
        return bodypart;
    }

    public void setBodypart(String bodypart) {
        this.bodypart = bodypart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

}
