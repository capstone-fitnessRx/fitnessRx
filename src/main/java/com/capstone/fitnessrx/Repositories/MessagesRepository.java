package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository  extends JpaRepository<Messages, Long> {
}
