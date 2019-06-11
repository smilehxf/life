package com.smile.life.repo;

import com.smile.life.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author: Smile
 * @date: 2019/6/6
 */
@Repository
public interface FoodRepo extends MongoRepository<Food, String> {
    Page<Food> findByFoodNameLike(String foodName, Pageable pageable);

    Page<Food> findByUsernameLike(String username, Pageable pageable);

    Page<Food> findByUploadDate(LocalDate uploadDate, Pageable pageable);

    Page<Food> findAll(Pageable pageable);


}
