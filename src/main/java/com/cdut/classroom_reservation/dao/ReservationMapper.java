package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationMapper {
    int deleteByPrimaryKey(Integer reservationId);

    int insert(Reservation record);

    int insertSelective(Reservation record);

    Reservation selectByPrimaryKey(Integer reservationId);

    int updateByPrimaryKeySelective(Reservation record);

    int updateByPrimaryKey(Reservation record);
}