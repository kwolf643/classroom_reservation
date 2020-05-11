package com.cdut.classroom_reservation.dao;

import com.cdut.classroom_reservation.entity.Exchange;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeMapper {
    int deleteByPrimaryKey(Integer exchangeId);

    int insert(Exchange record);

    int insertSelective(Exchange record);

    Exchange selectByPrimaryKey(Integer exchangeId);

    int updateByPrimaryKeySelective(Exchange record);

    int updateByPrimaryKey(Exchange record);
}