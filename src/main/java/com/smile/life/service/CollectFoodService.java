package com.smile.life.service;

import com.smile.life.entity.CollectFood;
import com.smile.life.repo.CollectFoodRepo;
import com.smile.life.utils.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/10
 */
@Service
public class CollectFoodService extends CrudService<CollectFood, CollectFoodRepo> {
    @Autowired
    CollectFoodRepo collectFoodRepo;

    @Override
    protected CollectFoodRepo getRepo() {
        return collectFoodRepo;
    }

    public List<CollectFood> findByUsername(String username) {
        return collectFoodRepo.findByUsername(username);
    }

    public List<CollectFood> findByFoodId(String foodId) {
        return collectFoodRepo.findByFoodId(foodId);
    }

    public void deleteAllByFoodId(String foodId) {
        collectFoodRepo.deleteAllByFoodId(foodId);
    }
}
