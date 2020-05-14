package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.ClassroomMapper;
import com.cdut.classroom_reservation.dao.ReservationMapper;
import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.entity.Reservation;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.result.gReservation;
import com.cdut.classroom_reservation.service.IMailService;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMailService mailService;

    //预约申请
    @Override
    public Result reserve(Reservation reservation) {
        if (reservation.getIdentityInt()==2){
            if(reservationMapper.getNumber(reservation.getUserId())>=3){return ResultFactory.buildFailResult("老师，你只有 3次 有效预约或有效申请！");}
        }
        if (reservation.getIdentityInt()==3){
            if(reservationMapper.getNumber(reservation.getUserId())>=1){return ResultFactory.buildFailResult("同学，你只有 1次 有效预约或有效申请！");}
        }
        if(classroomMapper.checkOpen(reservation)==0){return ResultFactory.buildFailResult("教室不存在或教室资源已锁定！");}
        else if(classroomMapper.checkClassroom(reservation)==0){return ResultFactory.buildFailResult("教室已被预约");}
        else if(reservationMapper.checkReservation(reservation)!=0){return ResultFactory.buildFailResult("教室已有人在预约中");}
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
            User user = userMapper.selectByPrimaryKey(re.get(i).getUserId());
            re.get(i).setUsername(user.getUsername());
            re.get(i).setIdentity(user.getIdentity());
        }

        gReservation rereservation =new gReservation();
        rereservation.setTotal(total);
        rereservation.setData(re);
        return  rereservation;
    }

    //更新预约
    @Override
    public Result updateReserve(Reservation reservation) {
        if(classroomMapper.checkOpen(reservation)==0){return ResultFactory.buildFailResult("教室不存在或教室资源已锁定！");}
        else if(classroomMapper.checkClassroom(reservation)==0){return ResultFactory.buildFailResult("教室已被预约");}
        else if(reservationMapper.checkReservation(reservation)!=0){return ResultFactory.buildFailResult("教室已有人在预约中");}
        else if(reservationMapper.updateByPrimaryKeySelective(reservation)!=0){
            reservation.setrTime(reservation.getrTime1int());
            classroomMapper.updateTime1(reservation);
            return ResultFactory.buildSuccessResult("预约已更新,请等待管理员审核！",null);
        }
        else return ResultFactory.buildFailResult("更新失败，请重试！");
    }

    //撤回
    @Override
    public Result changeStatus4(Reservation reservation) {
        int status = reservationMapper.updateStatus(reservation);
        if(status!=0){
            Reservation reservation1 = reservationMapper.selectByPrimaryKey(reservation.getReservationId());
            int state = classroomMapper.updateTime1(reservation1);
            if(state!=0){
                return ResultFactory.buildSuccessResult("已撤回！",null);
            }
            else return ResultFactory.buildFailResult("撤回失败，请重试！");
        }
        else return ResultFactory.buildFailResult("撤回失败，请重试！");
    }

    //预约失败
    @Override
    public Result changeStatus3(Reservation reservation) {
        int status = reservationMapper.updateStatus(reservation);
        if(status!=0){return ResultFactory.buildSuccessResult("已拒绝！",null);}
        else return ResultFactory.buildFailResult("网络错误，请重试！");
    }

    //预约通过
    @Override
    public Result changeStatus2(Reservation reservation) {
        int status = reservationMapper.updateStatus(reservation);
        if(status!=0){
            Reservation reservation1 = reservationMapper.selectByPrimaryKey(reservation.getReservationId());
            int state = classroomMapper.updateTime(reservation1);
            Classroom classroom = classroomMapper.selectByclassroomid(reservation1.getcRId());
            if(state!=0){
                User user=userMapper.selectByPrimaryKey(reservation1.getUserId());
                reservation1.setrTime1(reservation1.getrTime());
                String content= "</h1>预约成功：<h1>\n" +
                        "</h1>预约教室地址、时间段：<h1>\n" +
                        "<table border=\"1\">\n" +
                        "  <tr>\n" +
                        "    <th>预约人</th>\n" +
                        "    <th>预约教室</th>\n" +
                        "    <th>预约日期</th>\n" +
                        "    <th>预约时间段</th>\n" +
                        "    <th>预约类型</th>\n" +
                        "    <th>联系方式</th>\n" +
                        "    <th>备注</th>\n" +
                        "    <th>教室地址</th>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>"+user.getUsername()+"</td>\n" +
                        "    <td>"+reservation1.getcRId()+"</td>\n" +
                        "    <td>"+reservation1.getrDate()+"</td>\n" +
                        "    <td>"+reservation1.getrTime1()+"</td>\n" +
                        "    <td>"+reservation1.getrType()+"</td>\n" +
                        "    <td>"+reservation1.getrPhone()+"</td>\n" +
                        "    <td>"+reservation1.getRemarks()+"</td>\n" +
                        "    <td>"+classroom.getAddr()+"</td>\n" +
                        "  </tr>\n" +
                        "</table>";
                log.debug(user.getEmail());
                mailService.sendHtmlMail(user.getEmail(),"高校教室预约系统通知", content);
                return ResultFactory.buildSuccessResult("已通过！",null);
            }
            else return ResultFactory.buildFailResult("网络错误，请重试！");
        }
        else return ResultFactory.buildFailResult("网络错误，请重试！");
    }



}
