package com.smile.life.service;

import com.smile.life.entity.Food;
import com.smile.life.repo.FoodRepo;
import com.smile.life.utils.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    public Page<Food> findAll(Pageable pageable) {
        return foodRepo.findAll(pageable);
    }

    public Page<Food> findByFoodNameLike(String foodName, Pageable pageable) {
        return foodRepo.findByFoodNameLike(foodName, pageable);
    }

    public Page<Food> findByUsernameLike(String username, Pageable pageable) {
        return foodRepo.findByUsernameLike(username, pageable);
    }

    public Page<Food> findByUploadDate(LocalDate uploadDate, Pageable pageable) {
        return foodRepo.findByUploadDate(uploadDate, pageable);
    }

}
