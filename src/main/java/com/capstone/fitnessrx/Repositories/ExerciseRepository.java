package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
