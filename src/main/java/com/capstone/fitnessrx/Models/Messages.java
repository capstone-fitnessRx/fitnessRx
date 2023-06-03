package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Entity
public class Messages {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date timeStamp;
    @Id
    @GeneratedValue
    private Long id;


    public Messages() {
    }

    public Messages(User sender, User recipient, String content, Date timeStamp, Long id) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public Messages(User sender, User recipient, String content, Date timeStamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
