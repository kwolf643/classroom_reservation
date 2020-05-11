package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    private static Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    //    根据用户id和密码登录
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpSession session){
        Result result = userService.login(user,session);
        return result;
    }


}
