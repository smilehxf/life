package com.smile.life.service;

import com.smile.life.entity.Activity;
import com.smile.life.repo.ActivityRepo;
import com.smile.life.utils.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Smile
 * @date: 2019/6/9
 */
@Service
public class ActivityService extends CrudService<Activity, ActivityRepo> {
    @Autowired
    ActivityRepo activityRepo;

    @Override
    protected ActivityRepo getRepo() {
        return activityRepo;
    }
}
