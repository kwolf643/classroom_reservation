package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.ClassroomMapper;
import com.cdut.classroom_reservation.dao.ReservationMapper;
import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.result.gReservation;
import com.cdut.classroom_reservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    //预约申请
    @Override
    public Result reserve(Reservation reservation) {
        if(classroomMapper.checkOpen(reservation)==0){return ResultFactory.buildFailResult("教室不存在或教室资源已锁定！");}
        else if(classroomMapper.checkClassroom(reservation)==0){return ResultFactory.buildFailResult("教室已被预约");}
        else if(reservationMapper.insertSelective(reservation)!=0){
            return ResultFactory.buildSuccessResult("预约已提交,请等待管理员审核！",null);
        }
        else return ResultFactory.buildFailResult("网络错误，请重试！");
    }

    //查询预约
    @Override
    public gReservation getReservation(gReservation reservation) {
        log.debug("当前页数"+reservation.getCurPage());
        log.debug("页条数"+reservation.getPageSize());
        int para1=reservation.getCurPage()*reservation.getPageSize()-reservation.getPageSize();
        int para2=reservation.getCurPage()*reservation.getPageSize();
        reservation.setCurPage(para1);
        reservation.setPageSize(para2);

        int total = reservationMapper.getTotal(reservation);

        List<Reservation> re = reservationMapper.selectByIdAndDate(reservation);

        for(int i=0;i<re.size();i++)
        {
            re.get(i).setrTime1(re.get(i).getrTime());
            re.get(i).setrStatus1(re.get(i).getrStatus());
        }

        gReservation rereservation =new gReservation();
        rereservation.setTotal(total);
        rereservation.setData(re);
        return  rereservation;
    }




    public void reserveCheck(Reservation reservation) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(reservation.getcRId());
        classroom.setDate(reservation.getrDateformat());
    }

}
