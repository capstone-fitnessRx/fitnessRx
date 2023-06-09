package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.*;
import com.capstone.fitnessrx.Repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkoutController {

    private final WorkoutRepository workoutDao;
    private final UserRepository userDao;
    private final ExerciseDetailsRepository exerciseDetailsDao;
    private final ExerciseRepository exerciseDao;
    private final CalenderRepository calenderDao;



    public WorkoutController(WorkoutRepository workoutDao, UserRepository userDao, ExerciseDetailsRepository exerciseDetailsDao, ExerciseRepository exerciseDao, CalenderRepository calenderDao) {
        this.workoutDao = workoutDao;
        this.userDao = userDao;
        this.exerciseDetailsDao = exerciseDetailsDao;
        this.exerciseDao = exerciseDao;
        this.calenderDao = calenderDao;
    }


    @GetMapping("/my-workouts/{id}")
    public String getMyWorkouts(@PathVariable Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long id = user.getId();
        User userProfile = userDao.findById(id).orElse(null);
        model.addAttribute("userProfile", userProfile);

//        this gives navbar the profile pic
        String navbarProfilePic = user.getProfilePic();
        model.addAttribute("profilePicUrl", navbarProfilePic);

model.addAttribute("user", user);

        List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));

//        Workout userWorkouts = workoutDao.getReferenceById(id);
        model.addAttribute("myWorkouts", userWorkout);

        Workout workout = workoutDao.findById(id).orElse(null);


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


        return "index/myworkouts";
    }


    @GetMapping("/workouts-wall")

    public String getWorkoutWall(Model model, Principal principal) {
        User user = null;
        if(principal != null) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long id = user.getId();
            User userProfile = userDao.findById(id).orElse(null);
            model.addAttribute("userProfile", userProfile);
        }
        //        this gives navbar the profile pic

        List<Workout> allWorkout = workoutDao.findAll();
        model.addAttribute("allWorkouts", allWorkout);

//        Workout allWorkouts = workoutDao.getReferenceById(id);

//        Workout workout = workoutDao.findById(id).orElse(null);


//        User userProfile = userDao.findById(id).orElse(null);
        if (user != null) {
            String navbarProfilePic = user.getProfilePic();
            model.addAttribute("profilePicUrl", navbarProfilePic);
            model.addAttribute("user", user);
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
        }

        return "index/allworkouts";
    }


    @GetMapping("/workout-plan/{id}")
    public String getWorkoutPlan(@PathVariable Long id, Model model) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        this gives navbar the profile pic
//        long id = user.getId();
        User userProfile = userDao.findById(id).orElse(null);
        model.addAttribute("userProfile", userProfile);
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

        ArrayList<ExerciseDetails> exercises = (ArrayList<ExerciseDetails>) exerciseDetailsDao.findByWorkoutId(id);
        model.addAttribute("exercises", exercises);
        Workout userWorkout = workoutDao.getReferenceById(id);
        model.addAttribute("myWorkouts", userWorkout);
        String workoutPlanUrl = "/workout-plan/" + userWorkout.getId();
        model.addAttribute("workoutPlanUrl", workoutPlanUrl);

        return "index/workoutplan";
    }
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
        long id = user.getId();
        User userProfile = userDao.findById(id).orElse(null);
        model.addAttribute("userProfile", userProfile);
        //        this gives navbar the profile pic
        String navbarProfilePic = user.getProfilePic();
        model.addAttribute("profilePicUrl", navbarProfilePic);
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Workout workout = new Workout(title, description);
//        workout.setUser(user);
//        workoutDao.save(workout);
        for (ExerciseDetails exercise : exerciseDetailsList) {
//            System.out.println(exercise.getExercise_Api_Id());
            exerciseDetailsDao.save(exercise);
        }
        exerciseDetailsList.removeAll(exerciseDetailsList);
        return "redirect:/my-workouts/" + user.getId();
    }

//     @PostMapping("/workout/favorite")
//     public String setFavoriteWorkout() {// request param with a hidden input that has a th:value= workout id
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//         //connect request param to workouts
// }  //make the connection user to workout

    @GetMapping("/initialize-workout")
    public String getInitializeWorkout(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        User userProfile = userDao.findById(id).orElse(null);
        model.addAttribute("userProfile", userProfile);
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

//         return "redirect:/favorite/{id}"  + user.getId();

//    @PostMapping("/workout/favorites")
//    public String setFavoriteWorkout(@RequestParam Long addtofavorites) {
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user1 = new User(user);
//        List<Workout> newFavoriteWorkouts = user.getFavoriteWorkouts();
//        newFavoriteWorkouts.add(workoutDao.getReferenceById(addtofavorites));
//        user1.setFavoriteWorkouts(newFavoriteWorkouts);
//
//        userDao.save(user1);
//
//        return "redirect:/favorites/" + user.getId();
//    }



//<form th:action="@{workout/favorite}" method="post">
//    <div>
//    <input type="hidden" name>
//    <button type="submit">+ Favorites</button>
//    </div>
//
//    </form




    @PostMapping("/my-workouts/delete")
    public String deleteWorkout(@RequestParam long workoutId, @RequestParam long id) {
        Calender day = calenderDao.findByWorkout(workoutDao.getReferenceById(workoutId));
        if(day == null) {
            exerciseDetailsDao.deleteByWorkout(workoutDao.getReferenceById(workoutId));
            workoutDao.delete(workoutDao.getReferenceById(workoutId));
        } else {
        exerciseDetailsDao.deleteByWorkout(workoutDao.getReferenceById(workoutId));
        day.setWorkout(null);
        workoutDao.delete(workoutDao.getReferenceById(workoutId));

        }
//        calenderDao.delete(day);
        return "redirect:/my-workouts/" + id;
    }


//    @PostMapping("/workouts-wall/{workoutId}/edit")
//    public String editWorkout(Model model) {
//        return "redirect:/workouts-wall";
//







}
