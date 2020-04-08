package com.cfy.interestback;

import com.cfy.interestback.mapper.UserOperationMessageMapper;
import com.cfy.interestback.model.UserOperationMessage;
import com.cfy.interestback.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class OperationMessageTest {

    @Autowired
    private UserOperationMessageMapper userOperationMessageMapper;
    @Test
    public void getUser() {
        SearchVo searchVo = new SearchVo();
        searchVo.setStart("2020-02-14");
        searchVo.setEnd("2020-03-05");
        searchVo.setSearch("%c%");
        log.info("searchVo = " + searchVo);
        List<UserOperationMessage> list = userOperationMessageMapper.getList(searchVo);
        for (UserOperationMessage message : list) {
            log.info("message = " + message);
        }
    }
}
