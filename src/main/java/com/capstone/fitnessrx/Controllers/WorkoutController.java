package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.ExerciseDetails;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import com.capstone.fitnessrx.Repositories.ExerciseDetailsRepository;
import com.capstone.fitnessrx.Repositories.UserRepository;
import com.capstone.fitnessrx.Repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WorkoutController {

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;
    private final ExerciseDetailsRepository exerciseDetailsDao;

    public WorkoutController(WorkoutRepository workoutDao, UserRepository userDao, ExerciseDetailsRepository exerciseDetailsDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
        this.exerciseDetailsDao = exerciseDetailsDao;
    }


    @GetMapping("/my-workouts/{id}")

    public String getMyWorkouts(@PathVariable Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));

//        Workout userWorkouts = workoutDao.getReferenceById(id);
        model.addAttribute("myWorkouts", userWorkout);

        Workout workout = workoutDao.findById(id).orElse(null);


        User userProfile = userDao.findById(id).orElse(null);
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
//        String workoutPlanUrl = "/workout-plan/" + workout.getId();
//        model.addAttribute("workoutPlanUrl", workoutPlanUrl);



        return "index/myworkouts";
    }


    @GetMapping("/workouts-wall")

    public String getWorkoutWall(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Workout> allWorkout = workoutDao.findAll();
        model.addAttribute("allWorkouts", allWorkout);

//        Workout allWorkouts = workoutDao.getReferenceById(id);

//        Workout workout = workoutDao.findById(id).orElse(null);


//        User userProfile = userDao.findById(id).orElse(null);

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
//        String workoutPlanUrl = "/workout-plan/" + workout.getId();
//        model.addAttribute("workoutPlanUrl", workoutPlanUrl);

//        model.addAttribute("workoutTitle", workoutTitle);


        return "index/allworkouts";
    }



    @GetMapping("/workout-plan/{id}")
    public String getWorkoutPlan(@PathVariable Long id, Model model) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getOne((long) user.getId());

        System.out.println(currentUser.getUsername());

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

        Workout userWorkout = workoutDao.getReferenceById(id);
        model.addAttribute("myWorkouts", userWorkout);
        String workoutPlanUrl = "/workout-plan/" + userWorkout.getId();
        model.addAttribute("workoutPlanUrl", workoutPlanUrl);

//    List<ExerciseDetails> exerciseDetails = exerciseDetailsDao.findAll();
//    ExerciseDetails exerciseDetails = e
//        String exerciseName = exerciseDetailsDao.findBy(user.getId());
//    model.addAttribute("exerciseName", exerciseName);
// Exercise exercise = (Exercise) currentUser.getExercisesList();

// List<Exercise> currentExercise = currentUser.getExercisesList();
// List<Workout> currentWorkout = currentUser.getWorkoutsList();
// Exercise exercise1 = (Exercise) exercise.getReps();



//Exercise exercise = new Exercise();
// model.addAttribute("exercises", currentExercise);
// model.addAttribute("workouts", currentWorkout);


//String exerciseName = exercise.getExercise_name();
//model.addAttribute("exerciseName", exerciseName);
//
//String workoutPlanName = workout.getTitle();
//model.addAttribute("workoutPlanName", workoutPlanName);
//
//int exerciseReps = exerciseDetails.getReps();
//model.addAttribute("exerciseReps", exerciseReps);
//
//int exerciseSets = exerciseDetails.getSets();
//model.addAttribute("exerciseSets", exerciseSets);
//
//String exerciseEqupment = exercise.getExercise_equipment();
//model.addAttribute("exerciseEquipment", exerciseEqupment);

//        model.addAttribute("workout", new Workout());
//        model.addAttribute("exercise", new Exercise());
        return "index/workoutplan";
    }




    @GetMapping("/workout-builder")
    public String getBuilder(Model model) {
//        model.addAttribute("exerciseDetails", new ExerciseDetails());

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

    @GetMapping("/workout-builder-inator")
    public String getBuilderInator(Model model, @RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
        model.addAttribute("exercisedetails", new ExerciseDetails());
        ExerciseDetails test = new ExerciseDetails(reps, sets, exercise_Api_Id);
        model.addAttribute("test", test);
        model.addAttribute("reps", reps);
        model.addAttribute("sets", sets);
        model.addAttribute("exercise_Api_Id", exercise_Api_Id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return "index/workoutBuilderInator";
    }
    @PostMapping("/workout-builder-inator")
    public String postBuilderInator(Model model,@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
        ExerciseDetails exercisedetails = new ExerciseDetails(exercise_Api_Id, reps, sets);
        exerciseDetailsDao.save(exercisedetails);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "redirect:/workout-builder";
    }









}
