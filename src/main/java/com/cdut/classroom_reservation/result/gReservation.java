package com.cdut.classroom_reservation.result;


import com.cdut.classroom_reservation.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class gReservation {
    private int curPage;

    private int pageSize;

    private int total;

    private  String userId;

    private  String cRId;

    private Date rDate;

    private int rStatus;

    private List<Reservation> data;

    public Integer getrStatus() {
        return rStatus;
    }

    public void setrStatus(Integer rStatus) {
        this.rStatus = rStatus;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getuserId() {
        return userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public String getcRId() {
        return cRId;
    }

    public void setcRId(String cRId) {
        this.cRId = cRId;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public List<Reservation> getData() {
        return data;
    }

    public void setData(List<Reservation> data) {
        this.data = data;
    }

}
