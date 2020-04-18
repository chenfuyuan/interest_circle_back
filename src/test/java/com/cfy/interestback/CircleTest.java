package com.cfy.interestback;

import com.cfy.interestback.mapper.CircleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CircleTest {

    @Autowired
    private CircleMapper circleMapper;

}
