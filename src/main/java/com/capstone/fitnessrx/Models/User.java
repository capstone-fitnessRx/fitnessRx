package com.capstone.fitnessrx.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Collection;
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
    @Column(nullable = false)
    private String location;
    @Column()
    private String workoutPreference;
    @Column()
    private String bio;
    @Column()
    private String goal;
    @Column()
    private String cardColor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> post;
    @OneToMany(mappedBy = "user")
    private List<Calender> calender;
    @OneToMany(mappedBy = "user")
    private List<Ratings> ratings;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Messages> sentMessages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipient")
    private List<Messages> receivedMessages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userMain")
    @JsonBackReference
    private Collection<Friends> friendsAsMainUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userFriend")
    @JsonBackReference
    private Collection<Friends> friendsAsFriendUser;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<FavoriteExercise> favoriteExercises;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_exercise",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "exercise_id")})
    private List<Exercise> exercisesList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_workout",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")})
    private List<Workout> workoutsList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_favorite_workouts",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "workout_id")})
    private List<Workout> favoriteWorkouts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_favorite_exercises",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "exercise_id")})
    private List<Exercise> favoriteExercise;



    public User(User copy) {
        id =copy.id;
        username =copy.username;
        email =copy.email;
        password =copy.password;
        admin =copy.admin;
        location = copy.location;
        workoutPreference =copy.workoutPreference;
        bio =copy.bio;
        post =copy.post;
        goal = copy.goal;
        cardColor = copy.cardColor;
        calender = copy.calender;
        ratings =copy.ratings;
        sentMessages =copy.sentMessages;
        receivedMessages =copy.receivedMessages;
        friendsAsMainUser =copy.friendsAsMainUser;
        friendsAsFriendUser =copy.friendsAsFriendUser;
        favoriteWorkouts = copy.favoriteWorkouts;
        favoriteExercise =copy.favoriteExercise;
        exercisesList = copy.exercisesList;
        workoutsList = copy.workoutsList;
    }



    public User () {

    }

    public User(int id, String username, String email, String password, boolean admin, String location, String workoutPreference, String bio, String goal, String cardColor, List<Post> post, List<Calender> calender, List<Ratings> ratings, List<Messages> sentMessages, List<Messages> receivedMessages, Collection<Friends> friendsAsMainUser, Collection<Friends> friendsAsFriendUser, List<Workout> favoriteWorkouts, List<Exercise> favoriteExercise) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.location = location;
        this.workoutPreference = workoutPreference;
        this.bio = bio;
        this.goal = goal;
        this.cardColor = cardColor;
        this.post = post;
        this.calender = calender;
        this.ratings = ratings;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.friendsAsMainUser = friendsAsMainUser;
        this.friendsAsFriendUser = friendsAsFriendUser;
        this.favoriteWorkouts = favoriteWorkouts;
        this.favoriteExercise = favoriteExercise;
    }

    public User(String username, String email, String password, boolean admin, String location, String workoutPreference, String bio, String goal, String cardColor, List<Post> post, List<Calender> calender, List<Ratings> ratings, List<Messages> sentMessages, List<Messages> receivedMessages, Collection<Friends> friendsAsMainUser, Collection<Friends> friendsAsFriendUser, List<Workout> favoriteWorkouts, List<Exercise> favoriteExercise) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.location = location;
        this.workoutPreference = workoutPreference;
        this.bio = bio;
        this.goal = goal;
        this.cardColor = cardColor;
        this.post = post;
        this.calender = calender;
        this.ratings = ratings;
        this.sentMessages = sentMessages;
        this.receivedMessages = receivedMessages;
        this.friendsAsMainUser = friendsAsMainUser;
        this.friendsAsFriendUser = friendsAsFriendUser;
        this.exercisesList = exercisesList;
        this.favoriteWorkouts = favoriteWorkouts;
        this.favoriteExercise = favoriteExercise;
        this.workoutsList = workoutsList;
    }

//    public User(String username, String email, String password, boolean admin, String location, String workoutPreference, String bio, String goal, List<Post> post, List<Calender> calender, List<Ratings> ratings, List<Messages> sentMessages, List<Messages> receivedMessages, List<Friends> friendsAsMainUser, List<Friends> friendsAsFriendUser, List<Workout> favoriteWorkouts, List<Exercise> favoriteExercise) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.admin = admin;
//        this.location = location;
//        this.workoutPreference = workoutPreference;
//        this.bio = bio;
//        this.goal = goal;
//        this.post = post;
//        this.calender = calender;
//        this.ratings = ratings;
//        this.sentMessages = sentMessages;
//        this.receivedMessages = receivedMessages;
//        this.friendsAsMainUser = friendsAsMainUser;
//        this.friendsAsFriendUser = friendsAsFriendUser;
//        this.favoriteWorkouts = favoriteWorkouts;
//        this.favoriteExercise = favoriteExercise;
//    }

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

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    public List<Calender> getcalender() {
        return calender;
    }

    public void setcalender(List<Calender> calender) {
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

    public Collection<Friends> getFriendsAsMainUser() {
        return friendsAsMainUser;
    }

    public void setFriendsAsMainUser(Collection<Friends> friendsAsMainUser) {
        this.friendsAsMainUser = friendsAsMainUser;
    }

    public Collection<Friends> getFriendsAsFriendUser() {
        return friendsAsFriendUser;
    }

    public void setFriendsAsFriendUser(Collection<Friends> friendsAsFriendUser) {
        this.friendsAsFriendUser = friendsAsFriendUser;
    }

    public List<Workout> getFavoriteWorkouts() {
        return favoriteWorkouts;
    }

    public void setFavoriteWorkouts(List<Workout> favoriteWorkouts) {
        this.favoriteWorkouts = favoriteWorkouts;
    }

    public List<Exercise> getFavoriteExercise() {
        return favoriteExercise;
    }

    public void setFavoriteExercise(List<Exercise> favoriteExercise) {
        this.favoriteExercise = favoriteExercise;
    }

    public List<Calender> getCalender() {
        return calender;
    }

    public void setCalender(List<Calender> calender) {
        this.calender = calender;
    }

    public List<Exercise> getExercisesList() {
        return exercisesList;
    }

    public void setExercisesList(List<Exercise> exercisesList) {
        this.exercisesList = exercisesList;
    }
}
