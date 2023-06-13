package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import java.util.Calender;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Controller
public class ProfileController {

    private final UserRepository userDao;
    private final FriendsRepository friendsDao;
    private final WorkoutRepository workoutDao;



//    @Value("${filestack.api.key}")
//    private String filestackapi;

    public ProfileController(UserRepository userDao, FriendsRepository friendsDao, WorkoutRepository workoutDao) {
        this.userDao = userDao;
        this.friendsDao = friendsDao;
        this.workoutDao = workoutDao;
    }

    //                          1.GETMAPPING
    //                          2.Settings
    //GET MAPPING FOR           3.Messages
    //   PROFILE                4.User grab for messages (id)
    //                          5.add friends
    //                          6.update profile
    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {
        //Authenticates LOGGED IN USER
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //        this gives us the endpoint and id for html pages to reference
        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);
        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);
        String calenderUrl = "/calender/" + user.getId();
        model.addAttribute("calenderUrl", calenderUrl);
        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);
        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);
        String cardColor = user.getCardColor();
        model.addAttribute("cardColor", cardColor);
        // this gets the current users id and compares it to the url id, so we can match itn using thymeleaf
        User authenticatedUserId = user;
        model.addAttribute("authenticatedUserId", authenticatedUserId);
        model.addAttribute("urlUserId", id);

        User userProfile = userDao.findById(id).orElse(null);
//        Friends friendList = findFriendsByUserMainAndUserFriend(authenticatedUserId, userProfile);
        if (userProfile != null) {


//            model.addAttribute("filestackapi", filestackapi);

            //get exercise favorites



            String username = userProfile.getUsername();
            String email = userProfile.getEmail();
            String location = userProfile.getLocation();
            String workoutPreference = userProfile.getWorkoutPreference();
            String bio = userProfile.getBio();
            String goal = userProfile.getGoal();
//messaging friends
            Collection<Friends> friends = user.getFriendsAsMainUser();
            model.addAttribute("friends", friends);




            Collection<Friends> userFriends = friendsDao.findAllByUserMain(userProfile);


            List<Workout> userFavorites = userProfile.getFavoriteWorkouts();
            List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));


            List<Exercise> userFavoriteExercise = userProfile.getFavoriteExercise();
            model.addAttribute("favoriteExercise", userFavoriteExercise);

            model.addAttribute("favoriteWorkouts", userFavorites);
            model.addAttribute("myWorkouts", userWorkout);
            model.addAttribute("userFriends", userFriends);
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userProfileId", id);
            model.addAttribute("username", username);
            model.addAttribute("location", location);
            model.addAttribute("workoutPreference", workoutPreference);
            model.addAttribute("bio", bio);
            model.addAttribute("goal", goal);
            model.addAttribute("email", email);
            //            model.addAttribute("userId", userId);
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println(userProfile);
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~");



            return "index/profile";
        } else {

            return "error";
        }
    }
//
//POST SETTINGS FOR PROFILE
//
    @PostMapping("/profile/settings")
    public String settingsProfile(@RequestParam("newCardColor") String newCardColor) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        user.setCardColor(newCardColor);



        userDao.save(user);

        return "redirect:/profile/" + user.getId();
    }
    //
    //POST Grab user id for messages FOR PROFILE
    //
    @PostMapping("/users")
    public String retrieveUserId(@RequestParam("userId") Long userId, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Retrieve the user's ID from the database based on the provided userId
        // Add the user's ID to the model to display it in the view
        model.addAttribute("userId", userId);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("USER ID CLICKED" + userId);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Return the view name where you want to display the user's ID
        return "redirect:/profile/" + user.getId();
    }


    //
    //POST ADD FRIEND FOR PROFILE
    //
    @PostMapping("/profile/{id}")
    public String addFriend (@PathVariable Long id, @RequestParam(name="contactHidden") long addID, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newFriend = userDao.getOne(addID);

        User userProfile = userDao.findById(id).orElse(null);

        model.addAttribute("userProfile", id);

        Friends friends = new Friends();

        friends.setUserMain(user);
        friends.setUserFriend(newFriend);
        friendsDao.save(friends);

        return "redirect:/profile/" + user.getId();
    }

    //
    //POST Update Profile FOR PROFILE
    //
    @PostMapping("profile/upload")
    public String uploadProfile(@RequestParam(name = "stashFilestackURL") String uploadedProfilePic, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.getReferenceById((long) user.getId());


        user = userDao.getReferenceById((long) user.getId());
        user.setProfilePic(uploadedProfilePic);
        userDao.save(user);


        return "redirect:/profile/" + user.getId();
    }
    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam("newUsername") String newUsername, @RequestParam("newEmail") String newEmail, @RequestParam("newLocation") String newLocation, @RequestParam("newBio") String newBio, @RequestParam("newWorkoutPreference") String newWorkoutPreference, @RequestParam("newGoal") String newGoal,Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());





        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setLocation(newLocation);
        user.setBio(newBio);
        user.setGoal(newGoal);
        user.setWorkoutPreference(newWorkoutPreference);


        // Save the updated user to the database or perform any desired actions
        userDao.save(user);

        // Add a success message or any other necessary information to the model
        model.addAttribute("message", "Profile updated successfully!");

        // Redirect to the profile page or return a view
        return "redirect:/profile/" + user.getId();
    }

    @PostMapping("/profile/delete")
    public String deleteProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        userDao.delete(user);


        return "redirect:/profile/" + user.getId();
    }
}
