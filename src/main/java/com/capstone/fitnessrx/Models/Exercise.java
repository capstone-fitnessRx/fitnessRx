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




    @OneToMany(mappedBy = "exercise")
    private List<ExerciseDetails> exercisesDetails;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ex_cat",
            joinColumns = {@JoinColumn(name = "exercises_id")},
            inverseJoinColumns = {@JoinColumn(name = "cat_id")}
    )
    private List<Categories> categories;
    @Column(nullable = false, length = 100)
    private String target_muscle;
    @Column(nullable = false, length = 100)
    private String equipment;
    @Column(nullable = false, length = 100)
    private String bodypart;
    @Column(nullable = false)
    private String exerciseGif;
    @ManyToOne
    @JoinColumn(name = "favorite_exercise_id")
    private FavoriteExercise favoriteExercise;


    public Exercise() {
    }

    public Exercise(long id, String exercise_name, List<ExerciseDetails> exercisesDetails, List<Categories> categories, String target_muscle, String equipment, String bodypart, String exerciseGif, FavoriteExercise favoriteExercise) {
        this.id = id;
        this.exercise_name = exercise_name;
        this.exercisesDetails = exercisesDetails;
        this.categories = categories;
        this.target_muscle = target_muscle;
        this.equipment = equipment;
        this.bodypart = bodypart;
        this.exerciseGif = exerciseGif;
        this.favoriteExercise = favoriteExercise;
    }

    public Exercise(String exercise_name, List<ExerciseDetails> exercisesDetails, List<Categories> categories, String target_muscle, String equipment, String bodypart, String exerciseGif, FavoriteExercise favoriteExercise) {
        this.exercise_name = exercise_name;
        this.exercisesDetails = exercisesDetails;
        this.categories = categories;
        this.target_muscle = target_muscle;
        this.equipment = equipment;
        this.bodypart = bodypart;
        this.exerciseGif = exerciseGif;
        this.favoriteExercise = favoriteExercise;
    }

    public Exercise(String exercise_name, String target_muscle, String equipment, String exerciseGif) {
        this.exercise_name = exercise_name;
        this.target_muscle = target_muscle;
        this.equipment = equipment;
        this.exerciseGif = exerciseGif;
    }
    public String getExerciseGif() {
        return exerciseGif;
    }

    public void setExerciseGif(String exerciseGif) {
        this.exerciseGif = exerciseGif;
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

    public List<ExerciseDetails> getExercisesDetails() {
        return exercisesDetails;
    }

    public void setExercisesDetails(List<ExerciseDetails> exercisesDetails) {
        this.exercisesDetails = exercisesDetails;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

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

    public FavoriteExercise getFavoriteExercise() {
        return favoriteExercise;
    }

    public void setFavoriteExercise(FavoriteExercise favoriteExercise) {
        this.favoriteExercise = favoriteExercise;
    }
}
