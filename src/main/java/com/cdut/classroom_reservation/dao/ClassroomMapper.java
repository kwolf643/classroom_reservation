package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Classroom;

public interface ClassroomMapper {
    int deleteByPrimaryKey(String classroomId);

    int insert(Classroom record);

    int insertSelective(Classroom record);

    Classroom selectByPrimaryKey(String classroomId);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);
}