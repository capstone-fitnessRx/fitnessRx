package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final UserRepository userDao;
    private final CommentsRepository commentsDao;
    private final FriendsRepository friendsDao;
    private final MessagesRepository messagesDao;
    private final RatingsRepository ratingsDao;
    private final CalenderRepository calenderDao;
    private final WorkoutRepository workoutDao;
    private Model model;


    public MainController(UserRepository userDao, CommentsRepository commentsDao, FriendsRepository firendsDao, MessagesRepository messagesDao, RatingsRepository ratingsDao, CalenderRepository calenderDao, WorkoutRepository workoutDao) {
        this.userDao = userDao;
        this.commentsDao = commentsDao;
        this.friendsDao = firendsDao;
        this.messagesDao = messagesDao;
        this.ratingsDao = ratingsDao;
        this.calenderDao = calenderDao;
        this.workoutDao = workoutDao;
    }



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String getHello(Model model) {
        return "Hello partners";
    }






    @GetMapping("/home")

    public String getHome(Model model) {



        return "index/landingpage";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {



        return "index/profile";
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

    @GetMapping("/feed")

    public String getFeed(Model model) {



        return "index/feed";
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
