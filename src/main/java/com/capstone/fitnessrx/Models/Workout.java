package com.capstone.fitnessrx.Models;


import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "wo_ex_id", nullable = false)
    private ExerciseDetails exerciseDetails;

    @Column()
    private double avgRating;


    //    what is the correct property below???
    @Column()
    private String workoutImage;

    @OneToMany(mappedBy = "workout")
    private List<Ratings> ratings;


    @ManyToMany
    @JoinTable(
            name = "workout_categories",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Categories> categories;


    @ManyToMany(mappedBy = "favoriteWorkouts")
    private List<User> usersFavorites;


    public Workout() {
    }

    public Workout(Long id, User user, String title, String description, Date created, ExerciseDetails exerciseDetails, double avgRating, String workoutImage, List<Ratings> ratings, List<Categories> categories, List<User> usersFavorites) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.created = created;
        this.exerciseDetails = exerciseDetails;
        this.avgRating = avgRating;
        this.workoutImage = workoutImage;
        this.ratings = ratings;
        this.categories = categories;
        this.usersFavorites = usersFavorites;
    }

    public Workout(User user, String title, String description, Date created, ExerciseDetails exerciseDetails, double avgRating, String workoutImage, List<Ratings> ratings, List<Categories> categories, List<User> usersFavorites) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.created = created;
        this.exerciseDetails = exerciseDetails;
        this.avgRating = avgRating;
        this.workoutImage = workoutImage;
        this.ratings = ratings;
        this.categories = categories;
        this.usersFavorites = usersFavorites;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ExerciseDetails getExerciseDetails() {
        return exerciseDetails;
    }

    public void setExerciseDetails(ExerciseDetails exerciseDetails) {
        this.exerciseDetails = exerciseDetails;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public String getWorkoutImage() {
        return workoutImage;
    }

    public void setWorkoutImage(String workoutImage) {
        this.workoutImage = workoutImage;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<User> getUsersFavorites() {
        return usersFavorites;
    }

    public void setUsersFavorites(List<User> usersFavorites) {
        this.usersFavorites = usersFavorites;
    }
}