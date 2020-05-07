package com.cdut.classroom_reservation.service;

import com.cdut.classroom_reservation.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    //登录函数
    Map<String, String> login(User user, HttpSession session);
}
