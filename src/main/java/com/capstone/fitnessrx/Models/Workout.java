package com.capstone.fitnessrx.Models;


import jakarta.persistence.*;


import java.util.Date;

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
    private ExerciseDetails wo_ex;

    @Column()
    private double avgRating;


    //    what is the correct property below???
    @Column()
    private String workoutImage;

    public Workout() {
    }

    public Workout(Long id, User user, String title, String description, Date created, ExerciseDetails wo_ex, double avgRating, String workoutImage) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.created = created;
        this.wo_ex = wo_ex;
        this.avgRating = avgRating;
        this.workoutImage = workoutImage;
    }

    public Workout(User user, String title, String description, Date created, ExerciseDetails wo_ex, double avgRating, String workoutImage) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.created = created;
        this.wo_ex = wo_ex;
        this.avgRating = avgRating;
        this.workoutImage = workoutImage;
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

    public ExerciseDetails getWo_ex() {
        return wo_ex;
    }

    public void setWo_ex(ExerciseDetails wo_ex) {
        this.wo_ex = wo_ex;
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
}