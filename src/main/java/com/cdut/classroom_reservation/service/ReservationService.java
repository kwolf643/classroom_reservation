package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.gReservation;

public interface ReservationService {
    //申请预约函数
    Result reserve(Reservation reservation);

    //查询预约
    gReservation getReservation(gReservation reservation);

    //更新预约
    Result updateReserve(Reservation reservation);

    //撤回申请
    Result changeStatus4(Reservation reservation);

    //拒绝申请
    Result changeStatus3(Reservation reservation);

    //通过申请
    Result changeStatus2(Reservation reservation);
}
