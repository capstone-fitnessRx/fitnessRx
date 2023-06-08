package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


// this gets the current users id and compares it to the url id, so we can match itn using thymeleaf
        User authenticatedUserId = getAuthenticatedUser();
        model.addAttribute("authenticatedUserId", authenticatedUserId);
        model.addAttribute("urlUserId", id);


//        this gives us the endpoint and id for html pages to reference
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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("User" + user.getUsername());


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
        Collections.reverse(posts);
        List<Comments> comments = commentsDao.findAll();
        User userProfile = userDao.findById(id).orElse(null);


        if (userProfile != null) {
//
//            List<Post> post = postDao.findAll();

            model.addAttribute("post", new Post());
            model.addAttribute("comments", new Comments());


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

//        @PostMapping("/feed/{id}/comment")
//        public String commentCreate(@RequestParam(name = "comment") String content, @PathVariable Long id, Model model) {
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            user = userDao.getReferenceById((long) user.getId());
//            model.addAttribute("userProfileId", id);
//
//            Post post = postDao.getReferenceById((long) user.getId());
//
//            //Create a new comment object
//            Comments commentToDb = new Comments();
////            commentToDb.setContent(content);
//
//            commentToDb.setContent(commentToDb.getContent());
//            commentToDb.setUser(user);
//            commentToDb.setPosts(post);
//
//            commentsDao.save(commentToDb);
////            System.out.println("~~~~~~~~~~~~~~");
////            System.out.println("commentToDb.getContent() = " + commentToDb.getContent());
//
//            return "redirect:/feed/" + user.getId();
//        }
    @PostMapping("/feed/comment/create")
    public String commentvIICreate(@RequestParam(name="comment") String content, @RequestParam(name="postIdent") String postIdentNum) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        Long postId = Long.parseLong(postIdentNum);
        Post post = postDao.getReferenceById(postId);

        //Create a new comment object
        Comments newComment = new Comments();

        newComment.setContent(content);
        newComment.setUser(user);
        newComment.setPosts(post);

        commentsDao.save(newComment);

//            System.out.println("~~~~~~~~~~~~~~");
//            System.out.println("commentToDb.getContent() = " + commentToDb.getContent());

        return "redirect:/feed/" + user.getId();
    }

        @PostMapping("/feed/{id}")
        public String postCreate(@ModelAttribute Post newPost) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userDao.getReferenceById((long) user.getId());
//            User userIdUrl = userDao.findById(id).orElse(null);

            // Create a new Post object
            Post post = new Post();
//
            post.setContent(newPost.getContent());
            post.setUser(user);

            postDao.save(post);



            return "redirect:/feed/" + user.getId();
        }

    @GetMapping("/calendar/{id}")
    public String getCalender(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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

        User userAuth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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
//    @GetMapping("/exercise-display")

//@PostMapping("/exercise-display")
//public String postExerciseDisplay(Model model) {
//
//}

    @GetMapping("/workouts-wall")
    public String getWorkoutWall(Model model) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



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

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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


