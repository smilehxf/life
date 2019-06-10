package com.smile.life.repo;

import com.smile.life.entity.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Smile
 * @date: 2019/6/6
 */
@Repository
public interface FoodRepo extends MongoRepository<Food, String> {


}
