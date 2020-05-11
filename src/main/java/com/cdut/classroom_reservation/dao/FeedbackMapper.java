package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);
}