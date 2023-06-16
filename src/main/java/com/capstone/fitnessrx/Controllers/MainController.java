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
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarOutputStream;


@Controller
public class MainController {

    private final UserRepository userDao;
    private final CalenderRepository calenderDao;
//     private final WorkoutRepository workoutDao;
    //    private final FavoriteWorkoutRepository favworkDao;

    private final ExerciseRepository exerciseDao;

    private final WorkoutRepository workoutDao;

//    added Sunday night
//    private Calender existingCalender;

//     public MainController(UserRepository userDao, PostRepository postDao, CommentsRepository commentsDao, FriendsRepository friendsDao, MessagesRepository messagesDao, RatingsRepository ratingsDao, CalenderRepository calenderDao, WorkoutRepository workoutDao, ExerciseRepository exerciseDao, ExerciseDetailsRepository exerciseDetailsDao) {


    public MainController(UserRepository userDao, CalenderRepository calenderDao, ExerciseRepository exerciseDao, WorkoutRepository workoutDao) {
        this.userDao = userDao;
        this.calenderDao = calenderDao;
        this.exerciseDao = exerciseDao;
        this.workoutDao = workoutDao;


    }


    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    @ResponseBody
    public String getBye(Model model) {
        return "Bye partners";
    }

    @GetMapping("/")
    public String goHome() {
        return "index/landingpage";
    }


    @GetMapping("/home")
    public String getHome(Model model, Principal principal) {
        User user = null;
        if (principal != null) {
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

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

        return "index/landingpage";
    }


    @GetMapping("/calender/{id}")
    public String getCalender(@PathVariable Long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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


        String empty = "";
        if(calenderDao.findByUserAndDayId(user, 1).getWorkout() != null) {
            model.addAttribute("sundayT", calenderDao.findByUserAndDayId(user, 1).getWorkout().getTitle());
            model.addAttribute("sundayD", calenderDao.findByUserAndDayId(user, 1).getWorkout().getDescription());
        } else {
            model.addAttribute("sundayT", empty);
            model.addAttribute("sundayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 2).getWorkout() != null) {
            model.addAttribute("mondayT", calenderDao.findByUserAndDayId(user, 2).getWorkout().getTitle());
            model.addAttribute("mondayD", calenderDao.findByUserAndDayId(user, 2).getWorkout().getDescription());
        } else {
            model.addAttribute("mondayT", empty);
            model.addAttribute("mondayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 3).getWorkout() != null) {
            model.addAttribute("tuesdayT", calenderDao.findByUserAndDayId(user, 3).getWorkout().getTitle());
            model.addAttribute("tuesdayD", calenderDao.findByUserAndDayId(user, 3).getWorkout().getDescription());
        } else {
            model.addAttribute("tuesdayT", empty);
            model.addAttribute("tuesdayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 4).getWorkout() != null) {
            model.addAttribute("wednesdayT", calenderDao.findByUserAndDayId(user, 4).getWorkout().getTitle());
            model.addAttribute("wednesdayD", calenderDao.findByUserAndDayId(user, 4).getWorkout().getDescription());
        } else {
            model.addAttribute("wednesdayT", empty);
            model.addAttribute("wednesdayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 5).getWorkout() != null) {
            model.addAttribute("thursdayT", calenderDao.findByUserAndDayId(user, 5).getWorkout().getTitle());
            model.addAttribute("thursdayD", calenderDao.findByUserAndDayId(user, 5).getWorkout().getDescription());
        } else {
            model.addAttribute("thursdayT", empty);
            model.addAttribute("thursdayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 6).getWorkout() != null) {
            model.addAttribute("fridayT", calenderDao.findByUserAndDayId(user, 6).getWorkout().getTitle());
            model.addAttribute("fridayD", calenderDao.findByUserAndDayId(user, 6).getWorkout().getDescription());
        } else {
            model.addAttribute("fridayT", empty);
            model.addAttribute("fridayD", empty);
        }
        if(calenderDao.findByUserAndDayId(user, 7).getWorkout() != null) {
            model.addAttribute("saturdayT", calenderDao.findByUserAndDayId(user, 7).getWorkout().getTitle());
            model.addAttribute("saturdayD", calenderDao.findByUserAndDayId(user, 7).getWorkout().getDescription());
        } else {
            model.addAttribute("saturdayT", empty);
            model.addAttribute("saturdayD", empty);
        }
        List<Calender> calender = calenderDao.findAllByUser(user);
        List<Calender> newDay = new ArrayList<Calender>();
        for (Calender day : calender) {
            if (day.getWorkout() != null) {
                newDay.add(day);
            }
        }


        System.out.println(newDay.size());

        model.addAttribute("newDay", newDay);
        model.addAttribute("calender", calender);


        return "index/calender";
    }

//    @PostMapping("/calender/note")
//    public String saveNotes(@ModelAttribute Calender newCalender) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.getReferenceById((long) user.getId());

//    added Sunday night
//    public String saveNotes(@ModelAttribute("calender") Calender newCalender){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.getReferenceById((long) user.getId());
//
////        added Sunday night
//        if (existingCalender !=null) {
//            existingCalendar.setNotes(newCalender.getNotes());
//            calenderDao.save(existingCalender);
//        } else {
//            Calender calender = new Calender();
//            calender.setNotes(newCalender.getNotes());
//            calender.setUser(user);
//            calenderDao.save(calender);
//        }

//        originally had this...
//        Calender calender = new Calender();
//
//        calender.setNotes(newCalender.getNotes());
//        calender.setUser(user);
//        calenderDao.save(calender);
//
//
//        return "redirect:/calender/" + user.getId();

//    }


//     @GetMapping("/my-workouts/{id}")

//     public String getMyWorkouts(@PathVariable Long id, Model model) {
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//         List<Workout> userWorkout = workoutDao.findWorkoutsByUser(userDao.getReferenceById(id));

// //        Workout userWorkouts = workoutDao.getReferenceById(id);
//         model.addAttribute("myWorkouts", userWorkout);

//         Workout workout = workoutDao.findById(id).orElse(null);


//         User userProfile = userDao.findById(id).orElse(null);
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
// //        String workoutPlanUrl = "/workout-plan/" + workout.getId();
// //        model.addAttribute("workoutPlanUrl", workoutPlanUrl);


//         return "index/myworkouts";
//     }


    @GetMapping("/map/{location}")
    public String getMap(@PathVariable String location, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

//     @GetMapping("/workout-builder")
//     public String getBuilder(Model model) {
//         model.addAttribute("exerciseDetails", new ExerciseDetails());
//         model.addAttribute("exerciseDetailsList", exerciseDetailsList);
//         List<Exercise> allWorkoutExercises = exerciseDao.findAll();
//         model.addAttribute("allExercises", allWorkoutExercises);


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

// //    @GetMapping("/workout-builder-inator")
// //    public String getBuilderInator(Model model,@RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id) {
// //        model.addAttribute("exercisedetails", new ExerciseDetails());
// //        ExerciseDetails test = new ExerciseDetails(reps, sets, exercise_Api_Id);
// //        model.addAttribute("test", test);
// //        model.addAttribute("reps", reps);
// //        model.addAttribute("sets", sets);
// //        model.addAttribute("exercise_Api_Id", exercise_Api_Id);
// //        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// //
// //        return "index/workoutBuilderInator";
// //    }

//     public ArrayList<ExerciseDetails> exerciseDetailsList = new ArrayList<>();

//     @PostMapping("/workout-builder-inator")
//     public String postBuilderInator(Model model, @RequestParam int reps, @RequestParam int sets, @RequestParam int exercise_Api_Id, @RequestParam long exerciseId) {
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         ExerciseDetails exercisedetails = new ExerciseDetails(exercise_Api_Id, reps, sets, exerciseDao.getReferenceById(exerciseId), workoutDao.getReferenceById(8L));
//         exerciseDetailsList.add(exercisedetails);
// //        for (ExerciseDetails exercise : exerciseDetailsList) {
// ////            System.out.println(exercise.getExercise_Api_Id());
// //        }
// //        exerciseDetailsDao.save(exercisedetails);
//         return "redirect:/workout-builder";
//     }

//     @PostMapping("/workout-builder")
//     public String finalizeWorkout(Model model, @RequestParam String title, @RequestParam String description) {
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         Workout workout = new Workout(title, description);
//         workout.setUser(user);
//         workoutDao.save(workout);
//         for (ExerciseDetails exercise : exerciseDetailsList) {
//             System.out.println(exercise.getExercise_Api_Id());
//             exerciseDetailsDao.save(exercise);
//         }
//         exerciseDetailsList.removeAll(exerciseDetailsList);

//         return "redirect:/workout-builder";
//     }


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


//         Exercise ExerciseId = exerciseDao.getReferenceById(id);
//         model.addAttribute("displayExercise", ExerciseId);


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

//
//
//
//


//         return "index/exerciseDisplay";
//     }


//     @GetMapping("/workouts-wall")

//     public String getWorkoutWall(Model model) {
//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//         List<Workout> allWorkout = workoutDao.findAll();
//         model.addAttribute("allWorkouts", allWorkout);


// //        Workout allWorkouts = workoutDao.getReferenceById(id);


// //        Workout workout = workoutDao.findById(id).orElse(null);


// //        User userProfile = userDao.findById(id).orElse(null);

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
// //        String workoutPlanUrl = "/workout-plan/" + workout.getId();
// //        model.addAttribute("workoutPlanUrl", workoutPlanUrl);

// //        model.addAttribute("workoutTitle", workoutTitle);


//         return "index/allworkouts";
//     }


//     @GetMapping("/workout-plan/{id}")
//     public String getWorkoutPlan(@PathVariable Long id, Model model) {


//         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//         User currentUser = userDao.getOne((long) user.getId());

//         System.out.println(currentUser.getUsername());

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

//         Workout userWorkout = workoutDao.getReferenceById(id);
//         model.addAttribute("myWorkouts", userWorkout);
//         String workoutPlanUrl = "/workout-plan/" + userWorkout.getId();
//         model.addAttribute("workoutPlanUrl", workoutPlanUrl);

// //    List<ExerciseDetails> exerciseDetails = exerciseDetailsDao.findAll();
// //    ExerciseDetails exerciseDetails = e
// //        String exerciseName = exerciseDetailsDao.findBy(user.getId());
// //    model.addAttribute("exerciseName", exerciseName);
// // Exercise exercise = (Exercise) currentUser.getExercisesList();

// // List<Exercise> currentExercise = currentUser.getExercisesList();
// // List<Workout> currentWorkout = currentUser.getWorkoutsList();
// // Exercise exercise1 = (Exercise) exercise.getReps();


// //Exercise exercise = new Exercise();
// // model.addAttribute("exercises", currentExercise);
// // model.addAttribute("workouts", currentWorkout);


// //String exerciseName = exercise.getExercise_name();
// //model.addAttribute("exerciseName", exerciseName);
// //
// //String workoutPlanName = workout.getTitle();
// //model.addAttribute("workoutPlanName", workoutPlanName);
// //
// //int exerciseReps = exerciseDetails.getReps();
// //model.addAttribute("exerciseReps", exerciseReps);
// //
// //int exerciseSets = exerciseDetails.getSets();
// //model.addAttribute("exerciseSets", exerciseSets);
// //
// //String exerciseEqupment = exercise.getExercise_equipment();
// //model.addAttribute("exerciseEquipment", exerciseEqupment);


// //        model.addAttribute("workout", new Workout());
// //        model.addAttribute("exercise", new Exercise());
//         return "index/workoutplan";
//     }

    @GetMapping("/test")
    public String loading(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "index/TEST";
    }

    @PostMapping("/test")
    public String saveExercise(@ModelAttribute Exercise exercise) {

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

    @PostMapping("/calender/{id}")
    public String addToCalender(@PathVariable Long id, @RequestParam Long workoutId, @RequestParam int weekday) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userDao.getReferenceById((long) user.getId());

        Calender day = calenderDao.findByUserAndDayId(userDao.getReferenceById((long) user.getId()), weekday);
        System.out.println(day.getId());
        System.out.println(day.getDayId());
        System.out.println(day.getWorkout());
        System.out.println(day.getId());


        day.setWorkout(workoutDao.getReferenceById(workoutId));

        calenderDao.save(day);

        return "redirect:/calender/" + id;
    }

//    @GetMapping("/workouts/{workoutId}/calender")
//    public String showCalenderForm(@PathVariable("workoutID") Long workoutId, Model model) {
//
//
//
//
//
//        return "/calender/";

//    }
}


