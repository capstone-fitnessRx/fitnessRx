package com.capstone.fitnessrx.Repositories;

import com.capstone.fitnessrx.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
//    Categories findByCatName(String catName);
}
