package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String postToBeDeleted);
}
