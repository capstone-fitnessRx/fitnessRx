package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.Exercise;
import com.capstone.fitnessrx.Models.ExerciseDetails;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import com.capstone.fitnessrx.Repositories.ExerciseDetailsRepository;
import com.capstone.fitnessrx.Repositories.ExerciseRepository;
import com.capstone.fitnessrx.Repositories.UserRepository;
import com.capstone.fitnessrx.Repositories.WorkoutRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkoutController {

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;
    private final ExerciseDetailsRepository exerciseDetailsDao;
    private final ExerciseRepository exerciseDao;


    public WorkoutController(WorkoutRepository workoutDao, UserRepository userDao, ExerciseDetailsRepository exerciseDetailsDao, ExerciseRepository exerciseDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
        this.exerciseDetailsDao = exerciseDetailsDao;
        this.exerciseDao = exerciseDao;
    }


    @GetMapping("/my-workouts")
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


    //    @GetMapping("/workout-builder")
//    public String getBuilder(Model model) {
////        model.addAttribute("exerciseDetails", new ExerciseDetails());
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//
//        String profileUrl = "/profile/" + user.getId();
//        model.addAttribute("profileUrl", profileUrl);
//
//        String feedUrl = "/feed/" + user.getId();
//        model.addAttribute("feedUrl", feedUrl);
//
//        String calenderUrl = "/calender/" + user.getId();
//        model.addAttribute("calenderUrl", calenderUrl);
//
//        String myWorkoutsUrl = "/my-workouts/" + user.getId();
//        model.addAttribute("myWorkoutsUrl", myWorkoutsUrl);
//
//        String favoritesUrl = "/favorites/" + user.getId();
//        model.addAttribute("favoritesUrl", favoritesUrl);
//
//
//
//        return "index/workoutBuilder";
//    }
//
//    @PostMapping("/workout-builder-inator")
//    public String postBuilderInator(Model model,@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
//        ExerciseDetails exercisedetails = new ExerciseDetails(exercise_Api_Id, reps, sets);
//        exerciseDetailsDao.save(exercisedetails);
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return "redirect:/workout-builder";
//    }

//    @GetMapping("/workout-builder-inator")
//    public String getBuilderInator(Model model, @RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
//        model.addAttribute("exercisedetails", new ExerciseDetails());
//        ExerciseDetails test = new ExerciseDetails(reps, sets, exercise_Api_Id);
//        model.addAttribute("test", test);
//        model.addAttribute("reps", reps);
//        model.addAttribute("sets", sets);
//        model.addAttribute("exercise_Api_Id", exercise_Api_Id);
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return "index/workoutBuilderInator";
//    }
    @GetMapping("/workout-builder")
    public String getBuilder(Model model, @RequestParam String workoutNum) {
model.addAttribute("workoutNum", workoutNum);
        System.out.println("workoutNum = " + workoutNum);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("exerciseDetails", new ExerciseDetails());
        model.addAttribute("exerciseDetailsList", exerciseDetailsList);
        List<Exercise> allWorkoutExercises = exerciseDao.findAll();
        model.addAttribute("allExercises", allWorkoutExercises);

        return "index/workoutBuilder";
    }

    public ArrayList<ExerciseDetails> exerciseDetailsList = new ArrayList<>();

    @PostMapping("/workout-builder-inator")
    public String postBuilderInator(Model model, @RequestParam int reps, @RequestParam int sets, @RequestParam int exerciseId, @RequestParam String exerciseName, @RequestParam String workoutNum) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExerciseDetails exerciseDetails = new ExerciseDetails(exerciseName, exerciseId, reps, sets, exerciseDao.getReferenceById((long) exerciseId), workoutDao.getReferenceById(Long.valueOf(workoutNum)));
        exerciseDetailsList.add(exerciseDetails);

//        ExerciseDetails exerciseDetails = new ExerciseDetails(exerciseId, reps, sets);
//        for (ExerciseDetails exercise : exerciseDetailsList) {
////            System.out.println(exercise.getExercise_Api_Id());
//        }
        exerciseDetailsDao.save(exerciseDetails);
        return "redirect:/workout-builder?workoutNum=" + workoutNum;
    }

    @PostMapping("/workout-builder")
    public String finalizeWorkout(Model model) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Workout workout = new Workout(title, description);
//        workout.setUser(user);
//        workoutDao.save(workout);
        for (ExerciseDetails exercise : exerciseDetailsList) {
//            System.out.println(exercise.getExercise_Api_Id());
            exerciseDetailsDao.save(exercise);
        }
        exerciseDetailsList.removeAll(exerciseDetailsList);
        return "redirect:/workouts-wall";
    }

    @GetMapping("/initialize-workout")
    public String getInitializeWorkout(Model model) {
        return "index/initializeWorkout";
    }


    @PostMapping("/initialize-workout")
    public String initializeWorkout(Model model, @RequestParam String title, @RequestParam String description) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Workout workout = new Workout(title, description);
        workout.setUser(user);
        workoutDao.save(workout);

        System.out.println("workout.getId() = " + workout.getId());
        model.addAttribute("workoutNum", workout.getId());

        return "redirect:/workout-builder?workoutNum=" + workout.getId();
    }

    @PostMapping("/workouts-wall/{workoutId}/delete")
    public String deleteWorkout(Model model, @PathVariable long workoutId) {
        workoutDao.deleteById(workoutId);
        return "redirect:/workouts-wall";
    }

    @PostMapping("/workouts-wall/{workoutId}/edit")
    public String editWorkout(Model model) {
        return "redirect:/workouts-wall";
    }


}
