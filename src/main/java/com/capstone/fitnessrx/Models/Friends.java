package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
public class Friends {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id_1")
    private User userMain;

    @ManyToOne
    @JoinColumn(name = "users_id_2")
    private User userFriend;

    public Friends() {
    }

    public Friends(Long id, User userMain, User userFriend) {
        this.id = id;
        this.userMain = userMain;
        this.userFriend = userFriend;
    }

    public Friends(User userMain, User userFriend) {
        this.userMain = userMain;
        this.userFriend = userFriend;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUserMain() {
        return userMain;
    }

    public void setUserMain(User userMain) {
        this.userMain = userMain;
    }

    public User getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(User userFriend) {
        this.userFriend = userFriend;
    }
}
