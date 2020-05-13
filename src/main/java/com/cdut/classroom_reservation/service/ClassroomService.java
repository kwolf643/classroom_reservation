package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.gClassroom;

public interface ClassroomService {
    //查询教室
    gClassroom getClassroom(gClassroom classroom);

    //教室资源管理
    Result updateStatus(Classroom classroom);
}
