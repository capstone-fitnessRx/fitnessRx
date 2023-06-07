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


    @ManyToMany(mappedBy = "favoriteExercise")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_categories",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Categories> categories;

    public Exercise() {
    }

    public Exercise(String exercise_name, List<User> users, List<Categories> categories) {
        this.exercise_name = exercise_name;
        this.users = users;
        this.categories = categories;
    }

    public Exercise(long id, String exercise_name, List<User> users, List<Categories> categories) {
        this.id = id;
        this.exercise_name = exercise_name;
        this.users = users;
        this.categories = categories;
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