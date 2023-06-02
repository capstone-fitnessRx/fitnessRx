package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="favorite_exercise")
public class FavoriteExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favorite_exercise")
    private List<Exercise> ex_id;


    public FavoriteExercise() {
    }

    public FavoriteExercise(long id, User user, List<Exercise> ex_id) {
        this.id = id;
        this.user = user;
        this.ex_id = ex_id;
    }

    public FavoriteExercise(User user, List<Exercise> ex_id) {
        this.user = user;
        this.ex_id = ex_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Exercise> getEx_id() {
        return ex_id;
    }

    public void setEx_id(List<Exercise> ex_id) {
        this.ex_id = ex_id;
    }
}
