package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String exercise_name;

    @Column(nullable = false, length = 200)
    private String exercise_bodyPart;

    @Column(nullable = false, length = 200)
    private String exercise_equipment;

    @Column(nullable = false, length = 200)
    private String exercise_target;

    @Column(nullable = false, length = 200)
    private long exercise_id;

    @ManyToMany(mappedBy = "favoriteExercise")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_categories",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Categories> categories;

    public Exercise() {
    }

    public Exercise(long id, String exercise_name, String exercise_bodyPart, String exercise_equipment, String exercise_target, long exercise_id, List<User> users, List<Categories> categories) {
        this.id = id;
        this.exercise_name = exercise_name;
        this.exercise_bodyPart = exercise_bodyPart;
        this.exercise_equipment = exercise_equipment;
        this.exercise_target = exercise_target;
        this.exercise_id = exercise_id;
        this.users = users;
        this.categories = categories;
    }

    public Exercise(String exercise_name, String exercise_bodyPart, String exercise_equipment, String exercise_target, long exercise_id, List<User> users, List<Categories> categories) {
        this.exercise_name = exercise_name;
        this.exercise_bodyPart = exercise_bodyPart;
        this.exercise_equipment = exercise_equipment;
        this.exercise_target = exercise_target;
        this.exercise_id = exercise_id;
        this.users = users;
        this.categories = categories;
    }

    //
//    public Exercise(String exercise_name, List<User> users, List<Categories> categories) {
//        this.exercise_name = exercise_name;
//        this.users = users;
//        this.categories = categories;
//    }
//
//    public Exercise(long id, String exercise_name, List<User> users, List<Categories> categories) {
//        this.id = id;
//        this.exercise_name = exercise_name;
//        this.users = users;
//        this.categories = categories;
//    }

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

    public String getExercise_bodyPart() {
        return exercise_bodyPart;
    }

    public void setExercise_bodyPart(String exercise_bodyPart) {
        this.exercise_bodyPart = exercise_bodyPart;
    }

    public String getExercise_equipment() {
        return exercise_equipment;
    }

    public void setExercise_equipment(String exercise_equipment) {
        this.exercise_equipment = exercise_equipment;
    }

    public String getExercise_target() {
        return exercise_target;
    }

    public void setExercise_target(String exercise_target) {
        this.exercise_target = exercise_target;
    }

    public long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(long exercise_id) {
        this.exercise_id = exercise_id;
    }
}