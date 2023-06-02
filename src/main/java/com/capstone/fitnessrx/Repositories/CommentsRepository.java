package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository  extends JpaRepository<Comments, Long> {
}
