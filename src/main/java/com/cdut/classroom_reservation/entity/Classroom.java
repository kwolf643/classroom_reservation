package com.cdut.classroom_reservation.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Classroom {
    private Integer sorceId;

    private String classroomId;

    private Integer openStatus;

    private String addr;

    private Date date;

    private String time1;

    private String time2;

    private String time3;

    private String time4;

    private String time5;

    private String time6;

    public Integer getSorceId() {
        return sorceId;
    }

    public void setSorceId(Integer sorceId) {
        this.sorceId = sorceId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId == null ? null : classroomId.trim();
    }

    public String getOpenStatus() {
        if (openStatus==0){return "已锁定";}
        else return "开放预约" ;
    }

    public void setOpenStatus(Integer openStatus) {
        this.openStatus = openStatus;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String a = format.format(date);
        return  a;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1 == null ? null : time1.trim();
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2 == null ? null : time2.trim();
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3 == null ? null : time3.trim();
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4 == null ? null : time4.trim();
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5 == null ? null : time5.trim();
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6 == null ? null : time6.trim();
    }
}