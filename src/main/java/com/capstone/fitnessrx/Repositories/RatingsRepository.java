package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingsRepository  extends JpaRepository<Ratings, Long> {
}
