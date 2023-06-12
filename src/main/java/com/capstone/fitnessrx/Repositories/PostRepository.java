package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Post;
import com.capstone.fitnessrx.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Method signature for finding a post by its ID
    Optional<Post> findById(Long id);

    void deleteById(Post post);
}
