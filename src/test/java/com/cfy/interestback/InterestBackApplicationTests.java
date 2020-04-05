package com.cfy.interestback;

import com.cfy.interestback.mapper.UserMapper;
import com.cfy.interestback.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class InterestBackApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSearch() {
        List<User> listBySearch = userMapper.getListBySearch("4");
        log.info("list = " + listBySearch);
    }

}
