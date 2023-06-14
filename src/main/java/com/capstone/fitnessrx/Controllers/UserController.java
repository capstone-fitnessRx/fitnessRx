package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Models.Calender;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Repositories.CalenderRepository;
import com.capstone.fitnessrx.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    private final CalenderRepository calenderDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, CalenderRepository calenderDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.calenderDao = calenderDao;
    }

    @GetMapping("/register")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);

        for (int i = 1; i<=7 ; i++) {
            Calender calender = new Calender();
            calender.setUser(user);
            calender.setDayId(i);
            calenderDao.save(calender);
        }
        return "redirect:/login";
    }
}