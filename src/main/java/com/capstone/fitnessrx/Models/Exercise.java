package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String exerciseName;

    @Column(nullable = false, length = 200)
    private String exerciseBodyPart;

    @Column(nullable = false, length = 200)
    private String exerciseEquipment;

    @Column(nullable = false, length = 200)
    private String exerciseTarget;

    @Column(nullable = false, length = 200)
    private long exerciseId;

    @Column(nullable = false, length = 200)
    private String exerciseGif;

    @ManyToMany(mappedBy = "favoriteExercise")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_categories",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Categories> categories;

    public Exercise() {
    }


    public Exercise(long id, String exerciseName, String exerciseBodyPart, String exerciseEquipment, String exerciseTarget, long exerciseId, String exerciseGif, List<User> users, List<Categories> categories) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.exerciseBodyPart = exerciseBodyPart;
        this.exerciseEquipment = exerciseEquipment;
        this.exerciseTarget = exerciseTarget;
        this.exerciseId = exerciseId;
        this.exerciseGif = exerciseGif;
        this.users = users;
        this.categories = categories;
    }


    public Exercise(String exerciseName, String exerciseBodyPart, String exerciseEquipment, String exerciseTarget, long exerciseId, String exerciseGif, List<User> users, List<Categories> categories) {
        this.exerciseName = exerciseName;
        this.exerciseBodyPart = exerciseBodyPart;
        this.exerciseEquipment = exerciseEquipment;
        this.exerciseTarget = exerciseTarget;
        this.exerciseId = exerciseId;
        this.exerciseGif = exerciseGif;
        this.users = users;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseBodyPart() {
        return exerciseBodyPart;
    }

    public void setExerciseBodyPart(String exerciseBodyPart) {
        this.exerciseBodyPart = exerciseBodyPart;
    }

    public String getExerciseEquipment() {
        return exerciseEquipment;
    }

    public void setExerciseEquipment(String exerciseEquipment) {
        this.exerciseEquipment = exerciseEquipment;
    }

    public String getExerciseTarget() {
        return exerciseTarget;
    }

    public void setExerciseTarget(String exerciseTarget) {
        this.exerciseTarget = exerciseTarget;
    }

    public long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseGif() {
        return exerciseGif;
    }

    public void setExerciseGif(String exerciseGif) {
        this.exerciseGif = exerciseGif;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

}