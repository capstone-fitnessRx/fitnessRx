package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.Messages;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Repositories.MessagesRepository;
import com.capstone.fitnessrx.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MessagesController {

    private final MessagesRepository messagesDao;
    private final UserRepository userDao;


    public MessagesController(MessagesRepository messagesDao, UserRepository userDao) {
        this.messagesDao = messagesDao;
        this.userDao = userDao;
    }

    @GetMapping("/messages/{recipientId}")
    public String messageDisplay(@PathVariable Long recipientId, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("urlId", recipientId);
        User authenticatedUserId = user;

        model.addAttribute("authenticatedUserId", authenticatedUserId);

        long senderId = user.getId();

        Collection<Messages> messagesOther = messagesDao.findMessagesBySenderIdOrRecipientId(senderId, recipientId);
        Collection<Messages> messagesTo = messagesDao.findMessagesByRecipientIdOrSenderId(senderId, recipientId);

         List<Messages> combinedMessages = new ArrayList<>();

         combinedMessages.addAll(messagesOther);
         combinedMessages.addAll(messagesTo);


        model.addAttribute("messagesToMe", messagesOther);
        model.addAttribute("messagesFromMe", messagesTo);
        model.addAttribute("combinedMessages", combinedMessages);

        return "index/messages";
    }

    @PostMapping("/messages/{recipientId}")
    public String sendMessage(@PathVariable Long recipientId, @RequestParam(name = "senderId") String userIdentNum, @RequestParam(name = "receiverId") String recipientIdNum, @RequestParam(name = "content") String body, Model model) {
        model.addAttribute("urlId", recipientId);

        Long userIdNum = Long.parseLong(userIdentNum);
        User userId = userDao.getReferenceById(userIdNum);

        Long recipientNum= Long.parseLong(recipientIdNum);
        User recipient = userDao.getReferenceById(recipientNum);

        Messages newMessage = new Messages();

        newMessage.setTimeStamp(ZonedDateTime.now());
        newMessage.setSender(userId);
        newMessage.setRecipient(recipient);
        newMessage.setContent(body);

        messagesDao.save(newMessage);

        return "redirect:/messages/{recipientId}";
    }
}
