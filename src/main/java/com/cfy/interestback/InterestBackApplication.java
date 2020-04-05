package com.cfy.interestback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cfy.interestback.mapper")
public class InterestBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestBackApplication.class, args);
    }

}
