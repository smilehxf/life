package com.smile.life.repo;

import com.smile.life.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Smile
 * @date: 2019/6/9
 */
@Repository
public interface ActivityRepo extends MongoRepository<Activity, String> {

}
