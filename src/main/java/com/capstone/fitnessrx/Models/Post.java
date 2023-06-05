package com.capstone.fitnessrx.Models;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private long likes;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany
    @JoinColumn(name = "posts_id")
    private List<Comments> comments;

    public Post() {
    }

    public Post(long id, long likes, String content, User user, List<Comments> comments) {
        this.id = id;
        this.likes = likes;
        this.content = content;
        this.user = user;
        this.comments = comments;
    }

    public Post(long likes, String content, User user, List<Comments> comments) {
        this.likes = likes;
        this.content = content;
        this.user = user;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
