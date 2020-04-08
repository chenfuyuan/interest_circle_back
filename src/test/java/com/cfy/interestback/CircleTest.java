package com.cfy.interestback;

import com.cfy.interestback.mapper.CircleMapper;
import com.cfy.interestback.model.Circle;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CircleTest {

    @Autowired
    private CircleMapper circleMapper;
    @Test
    public void getList() {
        List<Circle> list = circleMapper.getList();
        List<Circle> delList = circleMapper.getDelList();

        log.info("list = ");
        for (Circle circle : list) {
            log.info("circle = " + circle);
        }

        log.info("============================");
        log.info("delList = ");
        for (Circle circle : delList) {
            log.info("circle = " + circle);
        }
    }
}
