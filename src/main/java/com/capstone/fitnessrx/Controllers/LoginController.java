
//package com.capstone.fitnessrx.Controllers;
//
//import com.capstone.fitnessrx.Models.User;
//import com.capstone.fitnessrx.Repositories.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class LoginController {
//    private final UserRepository userDao;
//
//    public LoginController(UserRepository userDao) {
//        this.userDao = userDao;
//    }
//
//    @GetMapping("/login")
//    public String showLogin(Model model) {
//        model.addAttribute("user", new User());
//        return "/profile";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user) {
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        userDao.save(user);
//        return "redirect:/login";
//    }
//}

