package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
