package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
@Table(name="Favorite_workout")
public class FavoriteWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @JoinColumn(name = "WO_id")
    private long WO_id;

    public FavoriteWorkout(long id, User user, long WO_id) {
        this.id = id;
        this.user = user;
        this.WO_id = WO_id;
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

    public long getWO_id() {
        return WO_id;
    }

    public void setWO_id(long WO_id) {
        this.WO_id = WO_id;
    }
}
