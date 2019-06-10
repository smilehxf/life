package com.smile.life.controller;

import com.smile.life.config.FileUpload;
import com.smile.life.entity.CollectFood;
import com.smile.life.entity.Food;
import com.smile.life.service.CollectFoodService;
import com.smile.life.service.FoodService;
import com.smile.life.utils.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/8
 */
@Controller
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    CollectFoodService collectFoodService;
    @Autowired
    GridFsTemplate gridFsTemplate;

    @PostMapping("/add")
    @ResponseBody
    public boolean add(Food food, String[] way, MultipartFile[] img) {
        try {
            List<String> imgUrl = FileUpload.upload(img, gridFsTemplate);
            food.setUploadDate(LocalDate.now());
            food.setMainImg(imgUrl.get(0));
            food.setGoodNum(0);
            List<Step> steps = new ArrayList<>();
            for (int i = 0; i < food.getStepNum(); i++) {
                steps.add(new Step(way[i], imgUrl.get(i + 1)));
            }
            food.setSteps(steps);
            System.out.println(food);
            foodService.save(food);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        foodService.delete(id);
        collectFoodService.deleteAllByFoodId(id);
        return "redirect:/food/0";
    }

    @GetMapping("/good/{id}/{username}")
    public String good(@PathVariable String id, @PathVariable String username) {
        Food food = foodService.findById(id);
        food.setGoodNum(food.getGoodNum() + 1);
        foodService.save(food);
        return "redirect:/detail/" + id + "/" + username;
    }

    @GetMapping("/favorite/{id}/{username}")
    public String favorite(@PathVariable String id, @PathVariable String username) {
        Food food = foodService.findById(id);
        collectFoodService.save(new CollectFood(username, id, food.getFoodName()));
        return "redirect:/detail/" + id + "/" + username;
    }

    @GetMapping("/cancel/favorite/{id}/{foodId}/{username}")
    public String cancel(@PathVariable String id, @PathVariable String foodId, @PathVariable String username) {
        collectFoodService.delete(id);
        return "redirect:/detail/" + foodId + "/" + username;
    }
}

