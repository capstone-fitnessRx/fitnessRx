package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Calender;
import com.capstone.fitnessrx.Models.User;
import com.capstone.fitnessrx.Models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalenderRepository extends JpaRepository<Calender, Long> {


    Calender findByUserAndDayId(User user, int dayId);
    Calender findByWorkout(Workout workout);

    List<Calender> findAllByUser(User user);
    List<Calender> findAllByUserAndDayId(User user, int id);




}
