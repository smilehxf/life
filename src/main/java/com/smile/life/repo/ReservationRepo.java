package com.smile.life.repo;

import com.smile.life.entity.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/10
 */
@Repository
public interface ReservationRepo extends MongoRepository<Reservation, String> {
    List<Reservation> findByUsername(String username);

    void deleteAllByAcitvityId(String acitvityId);

}
