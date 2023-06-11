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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarOutputStream;


@Controller
public class MainController {

    private final UserRepository userDao;
    private final CalenderRepository calenderDao;
    private final ExerciseRepository exerciseDao;




    public MainController(UserRepository userDao, CalenderRepository calenderDao, ExerciseRepository exerciseDao) {
        this.userDao = userDao;
        this.calenderDao = calenderDao;
        this.exerciseDao = exerciseDao;


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
//@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id

//     @GetMapping("/workout-builder")
//     public String getBuilder(Model model) {
// //        model.addAttribute("exerciseDetails", new ExerciseDetails());

//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


//         String profileUrl = "/profile/" + user.getId();
//         model.addAttribute("profileUrl", profileUrl);

//         String feedUrl = "/feed/" + user.getId();
//         model.addAttribute("feedUrl", feedUrl);

//         String calenderUrl = "/calender/" + user.getId();
//         model.addAttribute("calenderUrl", calenderUrl);

//         String myWorkoutsUrl = "/my-workouts/" + user.getId();
//         model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);

//         String favoritesUrl = "/favorites/" + user.getId();
//         model.addAttribute("favoritesUrl", favoritesUrl);



//         return "index/workoutBuilder";
//     }

//     @GetMapping("/workout-builder-inator")
//     public String getBuilderInator(Model model,@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
//         model.addAttribute("exercisedetails", new ExerciseDetails());
//         ExerciseDetails test = new ExerciseDetails(reps, sets, exercise_Api_Id);
//         model.addAttribute("test", test);
//         model.addAttribute("reps", reps);
//         model.addAttribute("sets", sets);
//         model.addAttribute("exercise_Api_Id", exercise_Api_Id);
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//         return "index/workoutBuilderInator";
//     }

//     public ArrayList<ExerciseDetails> exerciseDetailsList = new ArrayList<>();

//     @PostMapping("/workout-builder-inator")
//     public String postBuilderInator(Model model,@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         ExerciseDetails exercisedetails = new ExerciseDetails(exercise_Api_Id, reps, sets);
//         exerciseDetailsList.add(exercisedetails);
//         for(ExerciseDetails exercise : exerciseDetailsList) {
//             System.out.println(exercise.getExercise_Api_Id());
//         }
//         exerciseDetailsDao.save(exercisedetails);
//         return "redirect:/workout-builder";
//     }

//     @GetMapping("/exercise-page")
//     public String getExercise(Model model) {

// //        model.addAttribute("exercise", new Exercise());
//         List<Exercise> allExercises = exerciseDao.findAll();
//         model.addAttribute("allExercises", allExercises);

//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();





//         String profileUrl = "/profile/" + user.getId();
//         model.addAttribute("profileUrl", profileUrl);
//         String feedUrl = "/feed/" + user.getId();
//         model.addAttribute("feedUrl", feedUrl);
//         String calenderUrl = "/calender/" + user.getId();
//         model.addAttribute("calenderUrl", calenderUrl);
//         String myWorkoutsUrl = "/my-workouts/" + user.getId();
//         model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);
//         String favoritesUrl = "/favorites/" + user.getId();
//         model.addAttribute("favoritesUrl", favoritesUrl);

//         return "index/exercises";
//     }



    @GetMapping("/test")
    public String loading(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "index/TEST";
    }

    @PostMapping("/test")
    public String saveExercise(@ModelAttribute Exercise exercise){

        System.out.println(exercise.getExerciseBodyPart());
        String name = exercise.getExerciseName();
        exercise.setExerciseName(name);
//      System.out.println(exercise.getExercise_bodyPart());

//         String name = exercise.getExercise_name();
//         exercise.setExercise_name(name);


        String bodyPart = exercise.getExerciseBodyPart();
        exercise.setExerciseBodyPart(bodyPart);

        String equipment = exercise.getExerciseEquipment();
        exercise.setExerciseEquipment(equipment);

        String target = exercise.getExerciseTarget();
        exercise.setExerciseTarget(target);

        long id = exercise.getExerciseId();
        exercise.setExerciseId(id);

        exerciseDao.save(exercise);
        return "redirect:/test";
    }
}


