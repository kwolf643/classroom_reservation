package com.cdut.classroom_reservation.service.Impl;

import com.cdut.classroom_reservation.dao.ClassroomMapper;
import com.cdut.classroom_reservation.entity.Classroom;
import com.cdut.classroom_reservation.result.gClassroom;
import com.cdut.classroom_reservation.service.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ClassroomServiceImpl implements ClassroomService {
    private static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ClassroomMapper classroomMapper;

    //查询函数
    @Override
    public gClassroom getClassroom(gClassroom classroom){
        log.debug("展示教室");//测试后台接收数据
        log.debug("当前页数"+classroom.getCurPage());
        log.debug("页条数"+classroom.getPageSize());
        int para1=classroom.getCurPage()*classroom.getPageSize()-classroom.getPageSize();
        int para2=classroom.getCurPage()*classroom.getPageSize();
        classroom.setCurPage(para1);
        classroom.setPageSize(para2);

        int total = classroomMapper.getTotal(classroom);

        List<Classroom> re = classroomMapper.selectByIdAndDate(classroom);
        for(int i=0;i<re.size();i++)
        {
            re.get(i).setOpenStatus1(re.get(i).getOpenStatus());
        }
        gClassroom reclassroom =new gClassroom();
        reclassroom.setTotal(total);
        reclassroom.setData(re);
        return  reclassroom;
    }
}
