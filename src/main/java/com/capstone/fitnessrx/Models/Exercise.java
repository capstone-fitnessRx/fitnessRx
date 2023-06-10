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

    @Column(nullable = false, length = 200)
    private String exercise_gif;

    @ManyToMany(mappedBy = "favoriteExercise")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_details_reps",
            joinColumns = {@JoinColumn(name = "exercise_details_reps")},
            inverseJoinColumns = {@JoinColumn(name = "reps")})
    private List<ExerciseDetails> reps;

    @ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(name = "exercise_details_sets",
        joinColumns = {@JoinColumn(name = "exercise_details_sets")},
        inverseJoinColumns = {@JoinColumn(name = "sets")})
    private List<ExerciseDetails> sets;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_categories",
            joinColumns = {@JoinColumn(name = "exercise_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Categories> categories;

    @ManyToOne
    @JoinColumn(name = "user_exercise")
    private User user;



    public Exercise() {
    }

    public Exercise(long id, String exercise_name, String exercise_bodyPart, String exercise_equipment, String exercise_target, long exercise_id, String exercise_gif, List<User> users, List<ExerciseDetails> reps, List<ExerciseDetails> sets, List<Categories> categories, User user) {
        this.id = id;
        this.exercise_name = exercise_name;
        this.exercise_bodyPart = exercise_bodyPart;
        this.exercise_equipment = exercise_equipment;
        this.exercise_target = exercise_target;
        this.exercise_id = exercise_id;
        this.exercise_gif = exercise_gif;
        this.users = users;
        this.reps = reps;
        this.sets = sets;
        this.categories = categories;
        this.user = user;
    }

    public Exercise(String exercise_name, String exercise_bodyPart, String exercise_equipment, String exercise_target, long exercise_id, String exercise_gif, List<User> users, List<ExerciseDetails> reps, List<ExerciseDetails> sets, List<Categories> categories, User user) {
        this.exercise_name = exercise_name;
        this.exercise_bodyPart = exercise_bodyPart;
        this.exercise_equipment = exercise_equipment;
        this.exercise_target = exercise_target;
        this.exercise_id = exercise_id;
        this.exercise_gif = exercise_gif;
        this.users = users;
        this.reps = reps;
        this.sets = sets;
        this.categories = categories;
        this.user = user;
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

    public String getExercise_gif() {
        return exercise_gif;
    }

    public void setExercise_gif(String exercise_gif) {
        this.exercise_gif = exercise_gif;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ExerciseDetails> getReps() {
        return reps;
    }

    public void setReps(List<ExerciseDetails> reps) {
        this.reps = reps;
    }

    public List<ExerciseDetails> getSets() {
        return sets;
    }

    public void setSets(List<ExerciseDetails> sets) {
        this.sets = sets;
    }
}