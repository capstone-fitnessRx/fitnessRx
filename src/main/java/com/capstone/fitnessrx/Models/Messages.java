package com.capstone.fitnessrx.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class Messages {

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonManagedReference
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @JsonManagedReference
    private User recipient;

    @Column()
    private String content;

    @Column()
    private ZonedDateTime timeStamp;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Messages() {
    }

    public Messages(User sender, User recipient, String content, ZonedDateTime timeStamp, Long id) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timeStamp = timeStamp;
        this.id = id;
    }

    public Messages(User sender, User recipient, String content, ZonedDateTime timeStamp) {
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

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
