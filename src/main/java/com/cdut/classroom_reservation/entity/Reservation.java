package com.cdut.classroom_reservation.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private Integer reservationId;

    private String userId;

    private String cRId;

    private Date rDate;

    private Integer rTime;

    private Integer rTime1;

    private String rType;

    private String rPhone;

    private String remarks;

    private Integer rStatus;

    private Integer rStatus1;

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getcRId() {
        return cRId;
    }

    public void setcRId(String cRId) {
        this.cRId = cRId == null ? null : cRId.trim();
    }

    public String getrDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(rDate);
        return  date;
    }

    public Date getrDateformat() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public Integer getrTime(){return rTime;}

    public void setrTime(Integer rTime) {
        this.rTime = rTime;
    }

    public String getrTime1(){
        if(rTime1==1){return "1-2节";}
        else if(rTime1==2){return "3-4节";}
        else if(rTime1==3){return "5-6节";}
        else if(rTime1==4){return "7-8节";}
        else if(rTime1==5){return "9-10节";}
        else if(rTime1==6){return "11-12节";}
        else return null;}

    public void setrTime1(Integer rTime) {
        this.rTime1=rTime;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType == null ? null : rType.trim();
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone == null ? null : rPhone.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getrStatus() {
        return rStatus;
    }

    public void setrStatus(Integer rStatus) {
        this.rStatus = rStatus;
    }

    public String getrStatus1() {
        if(rStatus==1){return "审核中";}
        else if(rStatus==2){return "预约成功";}
        else if(rStatus==3){return "预约失败";}
        else return null;
    }

    public void setrStatus1(Integer rStatus) {
        this.rStatus1 = rStatus;
    }
}