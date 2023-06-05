//package com.capstone.fitnessrx.Models;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name="Favorite_workout")
//public class FavoriteWorkout {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    @OneToMany
//    @JoinColumn(name = "favorite_workout_id")
//    private List<Workout> WO_id;
//
//
//    public FavoriteWorkout() {
//    }
//
//    public FavoriteWorkout(long id, User user, List<Workout> WO_id) {
//        this.id = id;
//        this.user = user;
//        this.WO_id = WO_id;
//    }
//
//    public FavoriteWorkout(User user, List<Workout> WO_id) {
//        this.user = user;
//        this.WO_id = WO_id;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public List<Workout> getWO_id() {
//        return WO_id;
//    }
//
//    public void setWO_id(List<Workout> WO_id) {
//        this.WO_id = WO_id;
//    }
//}
