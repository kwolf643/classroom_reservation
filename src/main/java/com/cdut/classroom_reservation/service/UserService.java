package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;

import javax.servlet.http.HttpSession;

public interface UserService {
    //登录函数
    Result login(User user, HttpSession session);

    //更新用户
    Result update(User user);
}
