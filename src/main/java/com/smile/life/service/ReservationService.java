package com.smile.life.service;

import com.smile.life.entity.Reservation;
import com.smile.life.repo.ReservationRepo;
import com.smile.life.utils.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Smile
 * @date: 2019/6/10
 */
@Service
public class ReservationService extends CrudService<Reservation, ReservationRepo> {
    @Autowired
    ReservationRepo reservationRepo;

    @Override
    protected ReservationRepo getRepo() {
        return reservationRepo;
    }

    public List<Reservation> findByUsername(String username) {
        return reservationRepo.findByUsername(username);
    }

    public void deleteAllByAcitvityId(String acitvityId) {
        reservationRepo.deleteAllByAcitvityId(acitvityId);
    }
}
