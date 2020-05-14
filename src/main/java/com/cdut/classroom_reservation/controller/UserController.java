package com.cdut.classroom_reservation.controller;

import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    private static Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    //    根据用户id和密码登录
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpSession session){
        Result result = userService.login(user,session);
        return result;
    }

    // 修改密码
    @GetMapping("/updatePsd")
    @ResponseBody
    public Result updatePsd(@RequestParam(name = "password") String password,
                            @RequestParam(name = "newpassword") String newpassword,HttpServletRequest request){
        User user = new User();
        user.setPassword(password);


        //从session中直接获得  预约人ID
        HttpSession session=request.getSession();
        User user_session = (User) session.getAttribute("USER_SESSION");
        user.setUserId(user_session.getUserId());


        User user1 = userMapper.login_check(user);
        if(user1==null){return ResultFactory.buildFailResult("原始密码错误!"); }

        user.setPassword(newpassword);

        Result result = userService.update(user);
        return result;
    }

    // 修改用户信息
    @GetMapping("/updateUser")
    @ResponseBody
    public Result updateUser(@RequestParam(name = "phone") String phone,
                             @RequestParam(name = "email") String email,HttpServletRequest request){
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);

        //从session中直接获得  用户ID
        HttpSession session=request.getSession();
        User user_session = (User) session.getAttribute("USER_SESSION");
        user.setUserId(user_session.getUserId());


        Result result = userService.update(user);
        return result;
    }

}
