package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.result.gClassroom;

public interface ClassroomService {
    //查询教室
    gClassroom getClassroom(gClassroom classroom);
}
