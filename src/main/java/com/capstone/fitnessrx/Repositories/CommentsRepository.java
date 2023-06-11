package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Comments;
import com.capstone.fitnessrx.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository  extends JpaRepository<Comments, Long> {

        List<Comments> deleteCommentsByPosts_Id(long post);
    List<Comments> deleteCommentsByPosts_Id(Post post);
}
