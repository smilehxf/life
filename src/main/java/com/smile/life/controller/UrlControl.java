package com.smile.life.controller;

import com.smile.life.entity.Activity;
import com.smile.life.entity.CollectFood;
import com.smile.life.entity.Food;
import com.smile.life.entity.Reservation;
import com.smile.life.service.ActivityService;
import com.smile.life.service.CollectFoodService;
import com.smile.life.service.FoodService;
import com.smile.life.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UrlControl {
    @Autowired
    FoodService foodService;
    @Autowired
    ActivityService activityService;
    @Autowired
    CollectFoodService collectFoodService;
    @Autowired
    ReservationService reservationService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/food/{page}")
    public String showFood(@PathVariable Integer page, Model model) {
        long total = foodService.total();
        if (page == null) page = 0;
        List<Food> all = foodService.findAll(page, 8);
        System.out.println(all);
        model.addAttribute("total", total / 8);
        model.addAttribute("page", page);
        model.addAttribute("foods", all);
        return "food";
    }

    @GetMapping("/detail/{id}/{username}")
    public String detail(@PathVariable String id, Model model, @PathVariable String username) {
        Food food = foodService.findById(id);
        Boolean isExit = false;
        if (!username.equals("anonymousUser")) {
            List<CollectFood> favorites = collectFoodService.findByFoodId(id);
            for (CollectFood favorite : favorites) {
                if (favorite.getUsername().equals(username)) {
                    isExit = true;
                    model.addAttribute("collectId", favorite.getId());
                    break;
                }
            }
        }
        model.addAttribute("isExit", isExit);
        model.addAttribute("food", food);
        return "detail";
    }

    @GetMapping("/activity/{username}/{key}")
    public String activity(@PathVariable String key, Model model, @PathVariable String username) {
        List<Reservation> list = new ArrayList<>();
        if (username.equals("anonymousUser")) {
            model.addAttribute("isLogin", false);
        } else {
            model.addAttribute("isLogin", true);
            list = reservationService.findByUsername(username);
        }
        List<Activity> all = activityService.findAll(Sort.by(Sort.Direction.DESC, key));
        if (list.size() > 0) {
            for (Reservation reservation : list) {
                for (int i = 0; i < all.size(); i++) {
                    if (all.get(i).getId().equals(reservation.getAcitvityId())) {
                        Activity activity = all.get(i);
                        activity.setReservation(true);
                        activity.setReserveId(reservation.getId());
                        all.set(i, activity);
                    }
                }
            }
        }
        System.out.println(all);
        model.addAttribute("activities", all);
        return "activity";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/mylife/{username}")
    public String mylife(Model model, @PathVariable String username) {
        List<CollectFood> collectFoods = collectFoodService.findByUsername(username);
        List<Reservation> reservations = reservationService.findByUsername(username);
        model.addAttribute("collectFoods", collectFoods);
        model.addAttribute("reservations", reservations);
        return "mylife";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
