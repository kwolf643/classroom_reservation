package com.cdut.classroom_reservation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cdut.classroom_reservation.dao")
public class ClassroomReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomReservationApplication.class, args);
    }

}
