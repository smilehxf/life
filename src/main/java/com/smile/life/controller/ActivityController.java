package com.smile.life.controller;

import com.smile.life.config.FileUpload;
import com.smile.life.entity.Activity;
import com.smile.life.entity.Reservation;
import com.smile.life.service.ActivityService;
import com.smile.life.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/9
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    ReservationService reservationService;

    @PostMapping("/add")
    @ResponseBody
    public boolean add(Activity activity, MultipartFile[] img) {
        try {
            List<String> imgUrl = FileUpload.upload(img, gridFsTemplate);
            if (imgUrl != null) activity.setImgUrl(imgUrl);
            System.out.println(activity);
            activityService.save(activity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("/delete/{id}/{username}")
    public String delete(@PathVariable String id, @PathVariable String username) {
        activityService.delete(id);
        reservationService.deleteAllByAcitvityId(id);
        return "redirect:/activity/" + username + "/id";
    }

    @GetMapping("/reserve/{id}/{username}")
    public String reserve(@PathVariable String id, @PathVariable String username) {
        Activity activity = activityService.findById(id);
        reservationService.save(new Reservation(username, id, activity.getName()));
        return "redirect:/activity/" + username + "/id";
    }

    @GetMapping("/cancel/reserve/{id}/{username}")
    public String cancel(@PathVariable String id, @PathVariable String username) {
        reservationService.delete(id);
        return "redirect:/activity/" + username + "/id";
    }

    @GetMapping("/cancel/{id}/{username}")
    public String cancelActivity(@PathVariable String id, @PathVariable String username) {
        reservationService.delete(id);
        return "redirect:/mylife/" + username;
    }
}
