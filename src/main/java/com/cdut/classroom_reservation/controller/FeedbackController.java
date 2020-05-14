package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.entity.Feedback;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.result.gFeedback;
import com.cdut.classroom_reservation.service.FeedbackService;
import com.cdut.classroom_reservation.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class FeedbackController {
    private static Logger log= LoggerFactory.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;


    //获取反馈列表
    @GetMapping("/getFeedback")
    @ResponseBody
    public gFeedback getFeedback(@RequestParam(name = "curPage") int curPage,
                                 @RequestParam(name = "pageSize") int pageSize,
                                 @RequestParam(name = "fDate")  String  fDate, HttpServletRequest request) throws ParseException {
        gFeedback feedback=new gFeedback();
        feedback.setCurPage(curPage);
        feedback.setPageSize(pageSize);
        if (!fDate.equals("")){
            String TimeStart = fDate.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date1 = format.parse(TimeStart);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DAY_OF_MONTH, 1);            //利用Calendar 实现 Date日期+1天
            date1 = c.getTime();
            feedback.setrDate(date1);
        }

        //从session中直接获得  用户ID
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if(user.getIdentity()!=1){feedback.setuserId(user.getUserId());}

        gFeedback feedback1=feedbackService.getFeedback(feedback);
        return feedback1;
    }

    //添加反馈
    @GetMapping("/addFeedback")
    @ResponseBody
    public Result addFeedback(@RequestParam(name = "content") String content,
                              @RequestParam(name = "topic") String topic,
                              @RequestParam(name = "fPhone")  String  fPhone, HttpServletRequest request) throws ParseException {
        //从前端获取数据
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setTopic(topic);
        feedback.setfPhone(fPhone);

        //状态置为0：待查看
        feedback.setfStatus(0);

        //获取当前时间
        Date date = new Date();
        feedback.setfDate(date);

        //从session中直接获得  用户ID
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        feedback.setUserId(user.getUserId());

        Result result = feedbackService.addFeedback(feedback);
        return  result;

    }

    //撤回反馈
    @GetMapping("/deleteFeedback")
    @ResponseBody
    public Result deleteFeedback(@RequestParam(name = "feedbackId") int feedbackId, HttpServletRequest request) throws ParseException {
        //从前端获取数据
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);

        //从session中直接获得  用户ID 用于验证
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if(user.getIdentity()!=1){feedback.setUserId(user.getUserId());}

        Result result = feedbackService.deleteFeedback(feedback);
        return  result;

    }

    //删除反馈
    @GetMapping("/deleteFeedbackAdmin")
    @ResponseBody
    public Result deleteFeedbackAdmin(@RequestParam(name = "feedbackId") int feedbackId, HttpServletRequest request) throws ParseException {
        //从前端获取数据
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);

        //从session中直接获得  用户ID 用于验证
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if(user.getIdentity()==2){return ResultFactory.buildFailResult("老师，您没有权限！");}
        if(user.getIdentity()==3){return ResultFactory.buildFailResult("同学，您没有权限！");}
        Result result = feedbackService.deleteFeedback(feedback);
        return  result;

    }

    //反馈处理
    @GetMapping("/changeFeedback")
    @ResponseBody
    public Result changeFeedback(@RequestParam(name = "feedbackId") int feedbackId, HttpServletRequest request) throws ParseException {
        //从前端获取数据
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);

        //状态置为1：已阅
        feedback.setfStatus(1);

        //从session中获得身份，同学和老师无权限
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if(user.getIdentity()==2){return ResultFactory.buildFailResult("老师，您没有权限！");}
        if(user.getIdentity()==3){return ResultFactory.buildFailResult("同学，您没有权限！");}

        Result result = feedbackService.changeFeedback(feedback);
        return  result;

    }
}
