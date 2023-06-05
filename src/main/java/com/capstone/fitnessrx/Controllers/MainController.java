package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentsRepository commentsDao;
    private final FriendsRepository friendsDao;
    private final MessagesRepository messagesDao;
    private final RatingsRepository ratingsDao;
    private final CalenderRepository calenderDao;
    private final WorkoutRepository workoutDao;
//    private final FavoriteWorkoutRepository favworkDao;
    private final FavoriteExerciseRepository favexerDao;

    private Model model;


    public MainController(UserRepository userDao, PostRepository postDao, CommentsRepository commentsDao, FriendsRepository friendsDao, MessagesRepository messagesDao, RatingsRepository ratingsDao, CalenderRepository calenderDao, WorkoutRepository workoutDao, FavoriteExerciseRepository favexerDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentsDao = commentsDao;
        this.friendsDao = friendsDao;
        this.messagesDao = messagesDao;
        this.ratingsDao = ratingsDao;
        this.calenderDao = calenderDao;
        this.workoutDao = workoutDao;
        this.favexerDao = favexerDao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getHello(Model model) {
        return "Hello partners";
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    @ResponseBody
    public String getBye(Model model) {
        return "Bye partners";
    }


    @GetMapping("/home")

    public String getHome(Model model) {



        return "index/landingpage";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {

//        List<FavoriteWorkout> favoriteWorkout = favworkDao.findAll();
        List<FavoriteExercise> favoriteExercise = favexerDao.findAll();

        User userProfile = userDao.findById(id).orElse(null);


        if (userProfile != null) {
            List<Workout> userFavorites = userProfile.getFavoriteWorkouts();

            // Add the favoriteWorkouts list to the model
            model.addAttribute("favoriteWorkouts", userFavorites);

            String username = userProfile.getUsername();
            String location = userProfile.getLocation();
            String workoutPreference = userProfile.getWorkoutPreference();
            String bio = userProfile.getBio();
            String goal = userProfile.getGoal();
            // Retrieve the currently logged-in user's ID
//            Long userId = ((YourUserDetailsClass) authentication.getPrincipal()).getUserId();
//            User user = (User) authentication.getPrincipal();
            // Add the userProfile, userProfileId, and userId variables to the model


            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userProfileId", id);
            model.addAttribute("username", username);
            model.addAttribute("location", location);
            model.addAttribute("workoutPreference", workoutPreference);
            model.addAttribute("bio", bio);
            model.addAttribute("goal", goal);




//            model.addAttribute("userId", userId);

            // Return the template name
            return "index/profile";
        } else {
            // Handle the case when the user with the provided id is not found
            return "error"; // or any other error handling mechanism
        }
    }


    @GetMapping("/feed/{id}")
    public String getFeed(@PathVariable Long id, Model model) {
        List<Post> posts = postDao.findAll();
        User userProfile = userDao.findById(id).orElse(null);

        if (userProfile != null) {
            String username = userProfile.getUsername();
            String location = userProfile.getLocation();
            String workoutPreference = userProfile.getWorkoutPreference();
            String bio = userProfile.getBio();
            String goal = userProfile.getGoal();




            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userProfileId", id);
            model.addAttribute("username", username);
            model.addAttribute("location", location);
            model.addAttribute("workoutPreference", workoutPreference);
            model.addAttribute("bio", bio);
            model.addAttribute("goal", goal);

            model.addAttribute("posts", posts);

            return "index/feed";
        } else {
            // Handle the case when the user with the provided id is not found
            return "error"; // or any other error handling mechanism
        }
        }

    @GetMapping("/calender")
    public String getCalender() {



        return "index/calender";
    }

    @GetMapping("/my-workouts/{id}")

    public String getMyWorkouts(@PathVariable String id) {



        return "index/myworkouts";
    }

    @GetMapping("/map")

    public String getMap() {


        return "index/map";
    }

    @GetMapping("/favorites")

    public String getFavorites(Model model) {



        return "index/favorites";
    }

    @GetMapping("/workout-builder")

    public String getBuilder(Model model) {



        return "index/workoutbuilder";
    }

    @GetMapping("/exercise-page")

    public String getExercise(Model model) {



        return "index/exercise";
    }

    @GetMapping("/exercise-display")

    public String getExerciseDisplay(Model model) {



        return "index/specificexercise";
    }

    @GetMapping("/workouts-wall")

    public String getWorkoutWall(Model model) {



        return "index/workouts";
    }

    @GetMapping("/workout-plan")

    public String getWorkoutPlan(Model model) {



        return "index/workoutplan";
    }
}
