package com.cdut.classroom_reservation.result;


import com.cdut.classroom_reservation.entity.Feedback;

import java.util.Date;
import java.util.List;

public class gFeedback {
    private int curPage;

    private int pageSize;

    private int total;

    private  String userId;

    private Date fDate;

    private List<Feedback> data;

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

    public Date getrDate() {
        return fDate;
    }

    public void setrDate(Date fDate) {
        this.fDate = fDate;
    }

    public List<Feedback> getData() {
        return data;
    }

    public void setData(List<Feedback> data) {
        this.data = data;
    }
}
