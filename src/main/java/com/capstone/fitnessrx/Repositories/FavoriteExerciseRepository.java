package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.FavoriteExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteExerciseRepository   extends JpaRepository<FavoriteExercise, Long> {
}
