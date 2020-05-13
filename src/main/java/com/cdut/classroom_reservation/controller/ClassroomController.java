package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.result.gClassroom;
import com.cdut.classroom_reservation.service.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ClassroomController {
    private static Logger log= LoggerFactory.getLogger(ClassroomController.class);

    @Autowired
    private ClassroomService classroomService;

    //查询教室资源
    @GetMapping("/getClassroom")
    @ResponseBody
    public gClassroom getClassroom(@RequestParam(name = "curPage") int curPage,
                                   @RequestParam(name = "pageSize") int pageSize,
                                   @RequestParam(name = "classroomId") String classroomId,
                                   @RequestParam(name = "date")  String  date,
                                   @RequestParam(name = "openStatus")  int  openStatus) throws ParseException {
        gClassroom classroom=new gClassroom();
        classroom.setCurPage(curPage);
        classroom.setPageSize(pageSize);
        classroom.setOpenStatus(openStatus);
        if (!classroomId.equals("")){classroom.setClassroomId(classroomId);}
        if (!date.equals("")){
            String TimeStart = date.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date1 = format.parse(TimeStart);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DAY_OF_MONTH, 1);            //利用Calendar 实现 Date日期+1天
            date1 = c.getTime();
            classroom.setDate(date1);
        }
        gClassroom classroom1=classroomService.getClassroom(classroom);
        return classroom1;
    }

    //教室资源管理
    @GetMapping("updateClassroom")
    @ResponseBody
    public Result updateClassroom(@RequestParam(name = "classroomId") String classroomId,
                                  @RequestParam(name = "openStatus") int openStatus,
                                  @RequestParam(name = "firstDate") String firstDate,
                                  @RequestParam(name = "secondtDate") String secondtDate, HttpServletRequest request) throws ParseException {
        //从session中获得身份，同学和老师无权限
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        if(user.getIdentity()==2){return ResultFactory.buildFailResult("老师，您没有权限！");}
        if(user.getIdentity()==3){return ResultFactory.buildFailResult("同学，您没有权限！");}

        Classroom classroom=new Classroom();
        classroom.setClassroomId(classroomId);
        classroom.setOpenStatus(openStatus);

        //日期转换
        String TimeStart = firstDate.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date1 = format.parse(TimeStart);

        String TimeStart2 = secondtDate.replace("Z", " UTC");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date2 = format2.parse(TimeStart2);

        classroom.setDate(date1);
        classroom.setDate2(date2);

        Result result = classroomService.updateStatus(classroom);
        return result;
    }
}
