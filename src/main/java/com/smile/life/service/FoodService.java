package com.smile.life.service;

import com.smile.life.entity.Food;
import com.smile.life.repo.FoodRepo;
import com.smile.life.utils.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Smile
 * @date: 2019/6/6
 */
@Service
public class FoodService extends CrudService<Food, FoodRepo> {
    @Autowired
    FoodRepo foodRepo;

    @Override
    protected FoodRepo getRepo() {
        return foodRepo;
    }

    public long total() {
        return foodRepo.count();
    }


}
