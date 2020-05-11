package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.result.gReservation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationMapper {
    int deleteByPrimaryKey(Integer reservationId);

    int insert(Reservation record);

    int insertSelective(Reservation record);

    Reservation selectByPrimaryKey(Integer reservationId);

    int updateByPrimaryKeySelective(Reservation record);

    int updateByPrimaryKey(Reservation record);

    //预约列表
    List<Reservation> selectByIdAndDate(gReservation reservation);

    //预约总数
    int  getTotal(gReservation reservation);
}

