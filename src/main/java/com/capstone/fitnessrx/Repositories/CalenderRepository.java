package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Calender;
import com.capstone.fitnessrx.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<Calender, Long> {


    Calender findByUserAndDayId(User user, int dayId);



}
