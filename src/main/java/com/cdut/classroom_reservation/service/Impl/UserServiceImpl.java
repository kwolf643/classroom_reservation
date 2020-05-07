package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    //登录函数
    @Override
    public Map<String, String> login(User user, HttpSession session){
        log.debug("登录: "+user.getUsername());//测试后台接收数据
        User user1 = userMapper.selectByPrimaryKey(user.getUsername());
        Map<String, String> map = new HashMap<>();
        if (user1 !=null){
            map.put("msg","登录成功 欢迎您 "+user1.getUsername()+" !");
            session.setAttribute("USER_SESSION", user1);//登录成功，加入session
        }
        else map.put("msg","用户名或密码错误！");
        return map;
    }

}
