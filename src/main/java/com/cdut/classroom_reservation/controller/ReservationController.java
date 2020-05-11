package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.gReservation;
import com.cdut.classroom_reservation.service.ReservationService;
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
public class ReservationController {
    private static Logger log= LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    //预约申请
    @GetMapping("/reserve")
    @ResponseBody
    public Result reserve_commit(@RequestParam(name = "crid") String cRId,
                                 @RequestParam(name = "rDate") String rDate,
                                 @RequestParam(name = "rTime") int rTime,
                                 @RequestParam(name = "rType") String rType,
                                 @RequestParam(name = "rPhone") String rPhone,
                                 @RequestParam(name = "remarks") String remarks,HttpServletRequest request) throws ParseException {
        //获得前端数据
        Reservation reservation=new Reservation();
        reservation.setcRId(cRId);

        //日期转换
        String TimeStart = rDate.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date date1 = format.parse(TimeStart);

        reservation.setrDate(date1);
        reservation.setrTime(rTime);
        reservation.setrType(rType);
        reservation.setrPhone(rPhone);
        reservation.setRemarks(remarks);

        //预约状态置为1
        reservation.setrStatus(1);

        //从session中直接获得  预约人ID
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        reservation.setUserId(user.getUserId());

        //打印
        log.debug("预约人："+reservation.getUserId());
        log.debug("日期："+reservation.getrDate());
        log.debug("教室id:"+reservation.getcRId());

        //调用接口层函数
        Result result = reservationService.reserve(reservation);
        return result;
    }

    //获取预约列表
    @GetMapping("/getReservation")
    @ResponseBody
    public gReservation getReservation(@RequestParam(name = "curPage") int curPage,
                                     @RequestParam(name = "pageSize") int pageSize,
                                     @RequestParam(name = "cRId") String cRId,
                                     @RequestParam(name = "rDate")  String  rDate,HttpServletRequest request) throws ParseException {
        gReservation reservation=new gReservation();
        reservation.setCurPage(curPage);
        reservation.setPageSize(pageSize);
        if (!cRId.equals("")){reservation.setcRId(cRId);}
        if (!rDate.equals("")){
            String TimeStart = rDate.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            Date date1 = format.parse(TimeStart);
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            c.add(Calendar.DAY_OF_MONTH, 1);            //利用Calendar 实现 Date日期+1天
            date1 = c.getTime();
            reservation.setrDate(date1);
        }

        //从session中直接获得  预约人ID
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        reservation.setuserId(user.getUserId());

        gReservation reservation1=reservationService.getReservation(reservation);
        return reservation1;
    }
}
