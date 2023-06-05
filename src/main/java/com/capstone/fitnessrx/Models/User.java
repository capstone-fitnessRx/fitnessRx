package com.capstone.fitnessrx.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean admin;
    @Column()
    private String location;
    @Column()
    private String workoutPreference;
    @Column()
    private String bio;
    @Column()
    private String goal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> post;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FavoriteExercise> exercise;
    @OneToMany(mappedBy = "user")
    private List<Calender> calender;
    @OneToMany(mappedBy = "user")
    private List<Ratings> ratings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Messages> sentMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
    private List<Messages> receivedMessages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userMain")
    private List<Friends> friendsAsMainUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFriend")
    private List<Friends> friendsAsFriendUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FavoriteExercise> favoriteExercises;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="users_favorite_workouts",
    joinColumns={@JoinColumn(name="user_id")},
    inverseJoinColumns = {@JoinColumn(name="workout_id")})
    private List<Workout> favoriteWorkouts;

    public User() {
    }

    public User(int id, String username, String email, String password, boolean admin, String location, String workoutPreference, String bio, String goal, List<Post> post, List<FavoriteExercise> exercise, List<Calender> calender, List<Ratings> ratings, List<Messages> sentMessages, List<Messages> receivedMessages, List<Friends> friendsAsMainUser, List<Friends> friendsAsFriendUser, List<FavoriteExercise> favoriteExercises, List<Workout> favoriteWorkouts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.location = location;
        this.workoutPreference = workoutPreference;
        this.bio = bio;
        this.goal = goal;
        this.post = post;
        this.exercise = exercise;
        this.calender = calender;
        this.ratings = ratings;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.friendsAsMainUser = friendsAsMainUser;
        this.friendsAsFriendUser = friendsAsFriendUser;
        this.favoriteExercises = favoriteExercises;
        this.favoriteWorkouts = favoriteWorkouts;
    }

    public User(String username, String email, String password, boolean admin, String location, String workoutPreference, String bio, String goal, List<Post> post, List<FavoriteExercise> exercise, List<Calender> calender, List<Ratings> ratings, List<Messages> sentMessages, List<Messages> receivedMessages, List<Friends> friendsAsMainUser, List<Friends> friendsAsFriendUser, List<FavoriteExercise> favoriteExercises, List<Workout> favoriteWorkouts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.location = location;
        this.workoutPreference = workoutPreference;
        this.bio = bio;
        this.goal = goal;
        this.post = post;
        this.exercise = exercise;
        this.calender = calender;
        this.ratings = ratings;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.friendsAsMainUser = friendsAsMainUser;
        this.friendsAsFriendUser = friendsAsFriendUser;
        this.favoriteExercises = favoriteExercises;
        this.favoriteWorkouts = favoriteWorkouts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWorkoutPreference() {
        return workoutPreference;
    }

    public void setWorkoutPreference(String workoutPreference) {
        this.workoutPreference = workoutPreference;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<FavoriteExercise> getExercise() {
        return exercise;
    }

    public void setExercise(List<FavoriteExercise> exercise) {
        this.exercise = exercise;
    }

    public List<Calender> getCalender() {
        return calender;
    }

    public void setCalender(List<Calender> calender) {
        this.calender = calender;
    }

    public List<Ratings> getRatings() {
        return ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.ratings = ratings;
    }

    public List<Messages> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Messages> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Messages> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Messages> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Friends> getFriendsAsMainUser() {
        return friendsAsMainUser;
    }

    public void setFriendsAsMainUser(List<Friends> friendsAsMainUser) {
        this.friendsAsMainUser = friendsAsMainUser;
    }

    public List<Friends> getFriendsAsFriendUser() {
        return friendsAsFriendUser;
    }

    public void setFriendsAsFriendUser(List<Friends> friendsAsFriendUser) {
        this.friendsAsFriendUser = friendsAsFriendUser;
    }


    public List<FavoriteExercise> getFavoriteExercises() {
        return favoriteExercises;
    }

    public void setFavoriteExercises(List<FavoriteExercise> favoriteExercises) {
        this.favoriteExercises = favoriteExercises;
    }

    public List<Workout> getFavoriteWorkouts() {
        return favoriteWorkouts;
    }

    public void setFavoriteWorkouts(List<Workout> favoriteWorkouts) {
        this.favoriteWorkouts = favoriteWorkouts;
    }
}
