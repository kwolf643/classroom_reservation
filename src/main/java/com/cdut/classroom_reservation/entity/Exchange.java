package com.cdut.classroom_reservation.entity;

import java.util.Date;

public class Exchange {
    private Integer exchangeId;

    private Integer reservationId;

    private String exCId;

    private Date exDate;

    private Integer exTime;

    private Integer exUId;

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public String getExCId() {
        return exCId;
    }

    public void setExCId(String exCId) {
        this.exCId = exCId == null ? null : exCId.trim();
    }

    public Date getExDate() {
        return exDate;
    }

    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }

    public Integer getExTime() {
        return exTime;
    }

    public void setExTime(Integer exTime) {
        this.exTime = exTime;
    }

    public Integer getExUId() {
        return exUId;
    }

    public void setExUId(Integer exUId) {
        this.exUId = exUId;
    }
}