package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.FeedbackMapper;
import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.Feedback;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.result.gFeedback;
import com.cdut.classroom_reservation.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private UserMapper userMapper;

    //反馈列表
    @Override
    public gFeedback getFeedback(gFeedback feedback) {
        log.debug("当前页数"+feedback.getCurPage());
        log.debug("页条数"+feedback.getPageSize());
        int para1=feedback.getCurPage()*feedback.getPageSize()-feedback.getPageSize();
        int para2=feedback.getCurPage()*feedback.getPageSize();
        feedback.setCurPage(para1);
        feedback.setPageSize(para2);

        int total = feedbackMapper.getTotal(feedback);

        List<Feedback> re = feedbackMapper.selectByIdAndDate(feedback);

        for(int i=0;i<re.size();i++)
        {
            User user = userMapper.selectByPrimaryKey(re.get(i).getUserId());
            re.get(i).setUsername(user.getUsername());
            re.get(i).setIdentity(user.getIdentity());
        }

        gFeedback feedback1 =new gFeedback();
        feedback1.setTotal(total);
        feedback1.setData(re);
        return  feedback1;
    }

    //添加反馈
    @Override
    public Result addFeedback(Feedback feedback) {
        int state = feedbackMapper.insertSelective(feedback);
        if (state !=0){
            return ResultFactory.buildSuccessResult("添加成功!",null);
        }
        else return ResultFactory.buildFailResult("添加失败，请重试！");
    }

    //撤回反馈
    @Override
    public Result deleteFeedback(Feedback feedback) {
        return null;
    }
}
