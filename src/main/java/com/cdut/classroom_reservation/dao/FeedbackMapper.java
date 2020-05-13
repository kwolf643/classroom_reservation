package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Feedback;
import com.cdut.classroom_reservation.result.gFeedback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer feedbackId);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackId);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    //反馈总数
    int getTotal(gFeedback feedback);

    //反馈列表
    List<Feedback> selectByIdAndDate(gFeedback feedback);

    //删除反馈
    int deleteByPrimaryKeyAndUserId(Feedback feedback);

    //反馈处理
    int changeStauts(Feedback feedback);
}