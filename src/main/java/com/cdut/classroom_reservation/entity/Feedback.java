package com.cdut.classroom_reservation.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Feedback {
    private Integer feedbackId;

    private String userId;

    private String topic;

    private String content;

    private Integer fStatus;

    private Date fDate;

    private String fPhone;

    private String username;

    private Integer identity;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    public String getfDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(fDate);
        return  date;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    public String getfPhone() {
        return fPhone;
    }

    public void setfPhone(String fPhone) {
        this.fPhone = fPhone == null ? null : fPhone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIdentity() {
        if(identity==1){ return "管理员";}
        if(identity==2){ return "老师";}
        else return "学生";
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}