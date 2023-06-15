package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkoutRepository  extends JpaRepository<Workout, Long> {
    List<Workout> findWorkoutsByUser(User user);
    List<Workout> findWorkoutsById(Workout workouts);


}
