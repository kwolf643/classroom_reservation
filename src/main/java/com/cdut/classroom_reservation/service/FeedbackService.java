package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.entity.Feedback;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.gFeedback;

public interface FeedbackService {
    //反馈列表
    gFeedback getFeedback(gFeedback feedback);

    //添加反馈
    Result addFeedback(Feedback feedback);

    //撤回反馈
    Result deleteFeedback(Feedback feedback);

    //处理反馈
    Result changeFeedback(Feedback feedback);
}
