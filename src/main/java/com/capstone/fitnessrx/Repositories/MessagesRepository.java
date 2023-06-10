package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Messages;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MessagesRepository  extends JpaRepository<Messages, Long> {
    Collection<Messages> findAllById(Long userId);

    Collection<Messages> findAllById(User user);

    Collection<Messages> findMessagesById(Long messageId);
}
