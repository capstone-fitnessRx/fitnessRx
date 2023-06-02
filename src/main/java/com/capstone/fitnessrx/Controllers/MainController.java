package com.capstone.fitnessrx.Controllers;

import com.capstone.fitnessrx.Repositories.UserRepository;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {



    private final UserRepository userDao;

    public MainController(UserRepository userDao) {
        this.userDao = userDao;
    }
}
