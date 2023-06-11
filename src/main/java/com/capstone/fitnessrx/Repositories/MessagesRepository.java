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
    Collection<Messages> findAllByIdAndId(User user, Long recipientId);

    Collection<Messages> findMessagesById(Long messageId);

//    Collection<Messages> findMessagesByUserIdAndRecipientId(Long id, Long userId);

//    Collection<Messages> findMessagesByRecipientId(Long recipientId);
//    Collection<Messages> findMessagesBySenderId(Long senderId);

//    Collection<Messages> findMessagesBySenderIdOrRecipientId(Long senderId, Long recipientId);

    Collection<Messages> findMessagesByRecipientIdOrSenderId(Long recipientId, long senderId);

    Collection<Messages> findMessagesBySenderIdOrRecipientId(Long recipientId, long senderId);
//    Collection<Messages> findMessagesBySenderIdAndAndRecipientId(Long senderId, Long recipientId);
//    Collection<Messages> findMessagesByUserId(Long senderId);
//    Collection<Messages> findAllBySender(int user);
//    Collection<Messages> findAllByRecipient(int user);
//    Collection<Messages> findAllBySender(User user);

//    Collection<Messages> findMessagesBySenderAndRecipient(Long Sender, Long Recipient);
}
