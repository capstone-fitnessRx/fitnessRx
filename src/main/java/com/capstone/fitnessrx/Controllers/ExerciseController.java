package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.Exercise;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Repositories.ExerciseRepository;
import com.capstone.fitnessrx.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
@Controller
public class ExerciseController {
    private final UserRepository userDao;
    private final ExerciseRepository exerciseDao;

    public ExerciseController(UserRepository userDao, ExerciseRepository exerciseDao) {
        this.userDao = userDao;
        this.exerciseDao = exerciseDao;
    }


    @GetMapping("/exercise-page")
    public String getExercise(Model model, Principal principal) {
        User user = null;
        if(principal != null) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", user);
            long id = user.getId();
            User userProfile = userDao.findById(id).orElse(null);
            model.addAttribute("userProfile", userProfile);
        }
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Exercise> allExercises = exerciseDao.findAll();
        model.addAttribute("allExercises", allExercises);


        if (user != null) {
//        this gives navbar the profile pic
            String navbarProfilePic = user.getProfilePic();
            model.addAttribute("profilePicUrl", navbarProfilePic);

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
        return "index/exercises";

    }



    @GetMapping("/exercise-display/{id}")
    public String getExerciseDisplay(@PathVariable Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        User userProfile = userDao.findById(id).orElse(null);
        model.addAttribute("userProfile", userProfile);
//        model.addAttribute("exerciseName", exerciseName);
//        model.addAttribute("exerciseTarget", exerciseTarget);
//        model.addAttribute("exerciseEquipment", exerciseEquipment);
//        model.addAttribute("exerciseGif", exerciseGif);

//        this gives navbar the profile pic
        String navbarProfilePic = user.getProfilePic();
        model.addAttribute("profilePicUrl", navbarProfilePic);

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

//        List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));

        Exercise ExerciseId = exerciseDao.getReferenceById(id);
        model.addAttribute("displayExercise", ExerciseId);

        String exerciseName = ExerciseId.getExerciseName();
        model.addAttribute("exerciseName", exerciseName);
        String exerciseGif = ExerciseId.getExerciseGif();
        model.addAttribute("exerciseGif", exerciseGif);

//        Exercise exercise = exerciseDao.findById(id).orElse(null);
//        model.addAttribute("favoritesUrl", favoritesUrl);




        return "index/exerciseDisplay";
    }
}
