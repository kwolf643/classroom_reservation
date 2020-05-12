package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.result.gClassroom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomMapper {
    int deleteByPrimaryKey(Integer sorceId);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(Integer sorceId);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);

    //教室列表
    List<Classroom> selectByIdAndDate(gClassroom classroom);

    //教室资源总数
    int  getTotal(gClassroom classroom);

    //教室是否开放
    int checkOpen(Reservation reservation);

    //教室资源检查
    int checkClassroom(Reservation reservation);

    //预约情况上传
    int updateTime(Reservation reservation);

    //撤回情况上传
    int updateTime1(Reservation reservation);
}