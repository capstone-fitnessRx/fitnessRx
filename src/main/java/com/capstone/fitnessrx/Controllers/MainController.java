package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.*;
import com.capstone.fitnessrx.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import java.util.Calender;
import java.util.Collections;
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
    private ExerciseDetailsRepository exerciseDetailsDao = null;




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
        this.exerciseDetailsDao = exerciseDetailsDao;
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

            String calenderUrl = "/calender/" + user.getId();
            model.addAttribute("calenderUrl", calenderUrl);

            String myWorkoutsUrl = "/my-workouts/" + user.getId();
            model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

            String favoritesUrl = "/favorites/" + user.getId();
            model.addAttribute("favoritesUrl", favoritesUrl);
        }

        return "index/landingpage";
    }

    @PostMapping("/profile/settings")
    public String settingsProfile(@RequestParam("newCardColor") String newCardColor) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        user.setCardColor(newCardColor);



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

    @GetMapping("/profile/{id}")
    public String getProfile(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();




        String cardColor = user.getCardColor();
        model.addAttribute("cardColor", cardColor);

// this gets the current users id and compares it to the url id, so we can match itn using thymeleaf
        User authenticatedUserId = getAuthenticatedUser();
        model.addAttribute("authenticatedUserId", authenticatedUserId);
        model.addAttribute("urlUserId", id);


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

        String calenderUrl = "/calender/" + user.getId();
        model.addAttribute("calenderUrl", calenderUrl);

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


    @PostMapping("/feed/like")
    public String likePost(@RequestParam("like") Long like, @RequestParam(name="postIdent") String postIdentNum) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        // Retrieve the post from the database using the postId
        Long postId = Long.parseLong(postIdentNum);
        Post post = postDao.getReferenceById(postId);




        // Increment the like count for the post
        Long currentLikes = post.getLikes();
        Long newLikes = currentLikes + 1;
        post.setLikes(newLikes);

        // Update the post in the database or perform any desired actions
        postDao.save(post); // Assuming 'postDao' has a save method to persist the post changes

        // Redirect to a different page or return the same page
        return "redirect:/feed/" + user.getId();
    }

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

    @GetMapping("/calender/{id}")
    public String getCalender(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("calender", new Calender());

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

        return "index/calender";
    }

    @PostMapping("/calender/note")
    public String saveNotes(@ModelAttribute Calender newCalender){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getReferenceById((long) user.getId());

        Calender calender = new Calender();

        calender.setNotes(newCalender.getNotes());
        calender.setUser(user);
        calenderDao.save(calender);


        return "redirect:/calender/" + user.getId();

    }



    @GetMapping("/my-workouts/{id}")

    public String getMyWorkouts(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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



        return "index/myworkouts";
    }

    @GetMapping("/map/{location}")
    public String getMap(@PathVariable String location, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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

        String calenderUrl = "/calender/" + userAuth.getId();
        model.addAttribute("calenderUrl", calenderUrl);

        String myWorkoutsUrl = "/my-workouts/" + userAuth.getId();
        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

        String favoritesUrl = "/favorites/" + userAuth.getId();
        model.addAttribute("favoritesUrl", favoritesUrl);

        //getting user id
        User user = userDao.findById(id).orElse(null);

        assert user != null;

        //getting favorite exercises from user
        List<Exercise> userFavoriteExercise = user.getFavoriteExercise();

        //getting favorite workouts from user
        List<Workout> userFavorites = user.getFavoriteWorkouts();


        model.addAttribute("favoriteExercise", userFavoriteExercise);
        model.addAttribute("favoriteWorkout", userFavorites);

        return "index/favorites";
    }
//@RequestParam String reps, @RequestParam String sets, @RequestParam String exercise_Api_Id
    @GetMapping("/workout-builder")
    public String getBuilder(Model model) {
        model.addAttribute("exerciseDetails", new ExerciseDetails());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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



        return "index/workoutBuilder";
    }

    @PostMapping("/workout-builder")
    public String postBuilder(ExerciseDetails exerciseDetails) {
        int reps = exerciseDetails.getReps();
        exerciseDetails.setReps(reps);

        int sets = exerciseDetails.getSets();
        exerciseDetails.setSets(sets);

        int exercise_Api_Id = exerciseDetails.getExercise_Api_Id();
        exerciseDetails.setExercise_Api_Id(exercise_Api_Id);
        exerciseDetailsDao.save(exerciseDetails);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "redirect:index/workout-builder";
    }

    @GetMapping("/exercise-page")
    public String getExercise(Model model) {
        model.addAttribute("exercise", new Exercise());


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



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

        return "index/exercises";
    }

    @GetMapping("/exercise-display")
    public String getExerciseDisplay(Model model, @RequestParam String exerciseName, @RequestParam String exerciseTarget, @RequestParam String exerciseEquipment, @RequestParam String exerciseGif) {


        model.addAttribute("exerciseName", exerciseName);
        model.addAttribute("exerciseTarget", exerciseTarget);
        model.addAttribute("exerciseEquipment", exerciseEquipment);
        model.addAttribute("exerciseGif", exerciseGif);

        User user = getAuthenticatedUser();
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
        return "index/exerciseDisplay";
    }


    @GetMapping("/workouts-wall")
    public String getWorkoutWall(Model model) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();



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



        return "index/allworkouts";
    }

    @GetMapping("/workout-plan")
    public String getWorkoutPlan(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


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



        return "index/workoutplan";
    }

    @GetMapping("/test")
    public String loading(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "index/TEST";
    }

    @PostMapping("/test")
    public String saveExercise(@ModelAttribute Exercise exercise){
        System.out.println(exercise.getExercise_bodyPart());
        String name = exercise.getExercise_name();
        exercise.setExercise_name(name);

        String bodyPart = exercise.getExercise_bodyPart();
        exercise.setExercise_bodyPart(bodyPart);

        String equipment = exercise.getExercise_equipment();
        exercise.setExercise_equipment(equipment);

        String target = exercise.getExercise_target();
        exercise.setExercise_target(target);

        long id = exercise.getExercise_id();
        exercise.setExercise_id(id);

        exerciseDao.save(exercise);
        return "redirect:/test";
    }
}


