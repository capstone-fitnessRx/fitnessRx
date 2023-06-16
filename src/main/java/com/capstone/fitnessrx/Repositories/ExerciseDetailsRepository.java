package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Exercise;
import com.capstone.fitnessrx.Models.ExerciseDetails;
import com.capstone.fitnessrx.Models.Workout;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseDetailsRepository extends JpaRepository<ExerciseDetails, Long> {
    @Transactional
    void deleteByWorkout(Workout workout);

    List<ExerciseDetails> findByWorkoutId(long id);
}
