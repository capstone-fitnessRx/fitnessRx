package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Friends;
import com.capstone.fitnessrx.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendsRepository  extends JpaRepository<Friends, Long> {

    List<Friends> findAllByUserMain(User userProfile);
}
