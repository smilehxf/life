package com.smile.life.repo;

import com.smile.life.entity.CollectFood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/10
 */
@Repository
public interface CollectFoodRepo extends MongoRepository<CollectFood, String> {
    List<CollectFood> findByUsername(String username);

    List<CollectFood> findByFoodId(String foodId);

    void deleteAllByFoodId(String foodId);

}
