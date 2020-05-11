package com.cdut.classroom_reservation.result;


import com.cdut.classroom_reservation.entity.Classroom;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class gClassroom {
    private int curPage;

    private int pageSize;

    private int total;

    private  String classroomId;

    private Date date;

    private List<Classroom> data;


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

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Classroom> getData() {
        return data;
    }

    public void setData(List<Classroom> data) {
        this.data = data;
    }

}
