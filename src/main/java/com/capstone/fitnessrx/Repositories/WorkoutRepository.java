package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository  extends JpaRepository<Workout, Long> {
    List<Workout> findWorkoutsByUser(User user);
}
