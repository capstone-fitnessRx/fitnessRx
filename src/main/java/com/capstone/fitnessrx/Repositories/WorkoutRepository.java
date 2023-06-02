package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository  extends JpaRepository<Workout, Long> {
}
