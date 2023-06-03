package com.capstone.fitnessrx.Repositories;


import com.capstone.fitnessrx.Models.FavoriteWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteWorkoutRepository extends JpaRepository<FavoriteWorkout, Long> {
}
