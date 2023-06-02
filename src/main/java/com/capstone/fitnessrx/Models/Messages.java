package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
public class Messages {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date timeStamp;
    @Id
    @GeneratedValue
    private Long id;


    public Messages() {
    }


    public Messages(Users users, User user, String content, Date timeStamp, Long id) {
        this.users = users;
        this.user = user;
        this.content = content;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public Messages(Users users, User user, String content, Date timeStamp) {
        this.users = users;
        this.user = user;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
