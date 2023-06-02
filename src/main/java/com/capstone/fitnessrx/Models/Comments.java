package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

@Entity
public class Comments {
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private String content;

    @Column()
    private Long commentLikes;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Post posts;


    public Comments() {
    }

    public Comments(String content, Long commentLikes, User user, Post posts) {
        this.content = content;
        this.commentLikes = commentLikes;
        this.user = user;
        this.posts = posts;
    }

    public Comments(Long id, String content, Long commentLikes, User user, Post posts) {
        this.id = id;
        this.content = content;
        this.commentLikes = commentLikes;
        this.user = user;
        this.posts = posts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Long commentLikes) {
        this.commentLikes = commentLikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPosts() {
        return posts;
    }

    public void setPosts(Post posts) {
        this.posts = posts;
    }
}
