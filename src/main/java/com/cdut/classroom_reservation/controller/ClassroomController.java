package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.result.gClassroom;
import com.cdut.classroom_reservation.service.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
