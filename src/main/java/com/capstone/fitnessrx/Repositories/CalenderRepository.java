package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<Calender, Long> {


    //    added Sunday night
//    Calender findByUserID(int id);
//    Calender findByUserID(int id);
}
