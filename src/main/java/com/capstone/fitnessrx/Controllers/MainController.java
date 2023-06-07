package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ExerciseRepository exerciseDao;


    public MainController(UserRepository userDao, PostRepository postDao, CommentsRepository commentsDao, FriendsRepository friendsDao, MessagesRepository messagesDao, RatingsRepository ratingsDao, CalenderRepository calenderDao, WorkoutRepository workoutDao, ExerciseRepository exerciseDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentsDao = commentsDao;
        this.friendsDao = friendsDao;
        this.messagesDao = messagesDao;
        this.ratingsDao = ratingsDao;
        this.calenderDao = calenderDao;
        this.workoutDao = workoutDao;
        this.exerciseDao = exerciseDao;

    }


    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
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
        User user = getAuthenticatedUser();

        if (user != null) {
            String profileUrl = "/profile/" + user.getId();
            model.addAttribute("profileUrl", profileUrl);

            String feedUrl = "/feed/" + user.getId();
            model.addAttribute("feedUrl", feedUrl);

            String calendarUrl = "/calendar/" + user.getId();
            model.addAttribute("calendarUrl", calendarUrl);

            String myWorkoutsUrl = "/my-workouts/" + user.getId();
            model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

            String favoritesUrl = "/favorites/" + user.getId();
            model.addAttribute("favoritesUrl", favoritesUrl);
        }

        return "index/landingpage";
    }


    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {

        User user = getAuthenticatedUser();



        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);

//        List<FavoriteWorkout> favoriteWorkout = favworkDao.findAll();




        User userProfile = userDao.findById(id).orElse(null);
        List<Friends> userFriends = friendsDao.findAllByUserMain(userProfile);


        if (userProfile != null) {
            List<Workout> userFavorites = userProfile.getFavoriteWorkouts();
            List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));


            // Add the favoriteWorkouts list to the model
            model.addAttribute("favoriteWorkouts", userFavorites);
            model.addAttribute("myWorkouts", userWorkout);

            String username = userProfile.getUsername();
            String location = userProfile.getLocation();
            String workoutPreference = userProfile.getWorkoutPreference();
            String bio = userProfile.getBio();
            String goal = userProfile.getGoal();


            model.addAttribute("userFriends", userFriends);
            model.addAttribute("userProfile", userProfile);
            model.addAttribute("userProfileId", id);
            model.addAttribute("username", username);
            model.addAttribute("location", location);
            model.addAttribute("workoutPreference", workoutPreference);
            model.addAttribute("bio", bio);
            model.addAttribute("goal", goal);




//            model.addAttribute("userId", userId);


            return "index/profile";
        } else {

            return "error";
        }
    }

//tried making a post it gave 403 now i get 403 error when attempt to visit
    @GetMapping("/feed/{id}")
    public String getFeed(@PathVariable Long id, Model model) {

        User user = getAuthenticatedUser();

        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);


        List<Comments> comment = commentsDao.findAll();
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

            model.addAttribute("comments", comment);
            model.addAttribute("posts", posts);

            return "index/feed";
        } else {

            return "index/error";
        }
        }

    @GetMapping("/calendar/{id}")
    public String getCalender(@PathVariable Long id, Model model) {

        User user = getAuthenticatedUser();

        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);

            return "index/calendar";

    }

    @GetMapping("/my-workouts/{id}")

    public String getMyWorkouts(@PathVariable Long id, Model model) {

        User user = getAuthenticatedUser();

        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/myworkouts";
    }

    @GetMapping("/map/{location}")
    public String getMap(@PathVariable String location, Model model) {

        User user = getAuthenticatedUser();


        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);
        // Pass the location to the view
        model.addAttribute("location", location);


        return "index/gymmap";
    }

    @GetMapping("/favorites/{id}")
    public String getFavorites(@PathVariable Long id, Model model) {

        User userAuth = getAuthenticatedUser();


        String profileUrl = "/profile/" + userAuth.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + userAuth.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + userAuth.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + userAuth.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + userAuth.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);

        //getting user id
        User user = userDao.findById(id).orElse(null);
//        FavoriteExercise favoriteExercise1 = favexerDao.findById(id).orElse(null);
        assert user != null;
//        assert favoriteExercise1 != null;
        //creating list of favorite exercises
//        List<FavoriteExercise> favoriteExercise = user.getFavoriteExercises();

//        List<Exercise> exerciseList = favoriteExercise1.getExercises();

        //getting favorite exercises from user
        List<Exercise> userFavoriteExercise = user.getFavoriteExercise();

        //getting favorite workouts from user
        List<Workout> userFavorites = user.getFavoriteWorkouts();


        model.addAttribute("favoriteExercise", userFavoriteExercise);
        model.addAttribute("favoriteWorkout", userFavorites);

        return "index/favorites";
    }

    @GetMapping("/workout-builder")
    public String getBuilder(Model model) {

        User user = getAuthenticatedUser();


        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/workoutbuilder";
    }

    @GetMapping("/exercise-page")
    public String getExercise(Model model) {

        User user = getAuthenticatedUser();


        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/exercises";
    }

    @GetMapping("/exercise-display")
    public String getExerciseDisplay(Model model) {

        User user = getAuthenticatedUser();


        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/exerciseDisplay";
    }

    @GetMapping("/workouts-wall")
    public String getWorkoutWall(Model model) {

        User user = getAuthenticatedUser();



        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/allworkouts";
    }

    @GetMapping("/workout-plan")
    public String getWorkoutPlan(Model model) {

        User user = getAuthenticatedUser();


        String profileUrl = "/profile/" + user.getId();
        model.addAttribute("profileUrl", profileUrl);

        String feedUrl = "/feed/" + user.getId();
        model.addAttribute("feedUrl", feedUrl);

        String calendarUrl = "/calendar/" + user.getId();
        model.addAttribute("calendarUrl", calendarUrl);

        String myWorkoutsUrl = "/my-workouts/" + user.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + user.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);



        return "index/workoutplan";
    }
}


