package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.UserMapper;
import com.cdut.classroom_reservation.entity.User;
import com.cdut.classroom_reservation.result.Result;
import com.cdut.classroom_reservation.result.ResultFactory;
import com.cdut.classroom_reservation.service.IMailService;
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

    @Autowired
    private IMailService mailService;

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
            user1.setPassword("");
            return ResultFactory.buildSuccessResult(msg,user1);
        }
        else return ResultFactory.buildFailResult("登录失败，用户id或密码错误！");
    }

    //修改个人信息
    @Override
    public Result update(User user) {
        int state = userMapper.updateByPrimaryKeySelective(user);
        if(state!=0){
            User user1=userMapper.selectByPrimaryKey(user.getUserId());
            String identity = "";
            if(user1.getIdentity()==1){ identity= "管理员";}
            if(user1.getIdentity()==2){ identity= "老师";}
            if(user1.getIdentity()==3){ identity= "学生";}
            String content= "</h1>个人信息修改成功：<h1>\n" +
                    "</h1>您最新的个人信息：<h1>\n" +
                    "<table border=\"1\">\n" +
                    "  <tr>\n" +
                    "    <th>账号</th>\n" +
                    "    <th>姓名</th>\n" +
                    "    <th>身份</th>\n" +
                    "    <th>密码</th>\n" +
                    "    <th>邮箱</th>\n" +
                    "    <th>联系方式</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>"+user1.getUserId()+"</td>\n" +
                    "    <td>"+user1.getUsername()+"</td>\n" +
                    "    <td>"+identity+"</td>\n" +
                    "    <td>"+user1.getPassword()+"</td>\n" +
                    "    <td>"+user1.getEmail()+"</td>\n" +
                    "    <td>"+user1.getPhone()+"</td>\n" +
                    "  </tr>\n" +
                    "</table>";
            mailService.sendHtmlMail(user1.getEmail(),"高校教室预约系统通知", content);
            user1.setPassword("");
            return ResultFactory.buildSuccessResult("更新成功！",user1);
        }
        else  return ResultFactory.buildFailResult("更新失败，请重试！");
    }

}
