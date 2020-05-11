package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    //登录函数
    @Override
    public Result login(User user, HttpSession session){
        log.debug("登录: "+user.getUserId());//测试后台接收数据
        User user1 = userMapper.login_check(user);
        if (user1 !=null){
            session.setAttribute("USER_SESSION", user1);//登录成功，加入session
            String identity;
            if (user1.getIdentity()==1){identity="管理员";}
            else if (user1.getIdentity()==2){identity="老师";}
            else identity="同学";
            String msg="登录成功! 欢迎您,"+user1.getUsername()+" "+identity;
            log.debug(msg);
            return ResultFactory.buildSuccessResult(msg,user1);
        }
        else return ResultFactory.buildFailResult("登录失败，用户id或密码错误！");
    }

}
