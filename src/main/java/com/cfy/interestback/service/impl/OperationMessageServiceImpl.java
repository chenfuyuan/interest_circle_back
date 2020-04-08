package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.ArticleOperationMessageMapper;
import com.cfy.interestback.mapper.CircleOperationMessageMapper;
import com.cfy.interestback.mapper.UserOperationMessageMapper;
import com.cfy.interestback.model.ArticleOperationMessage;
import com.cfy.interestback.model.CircleOperationMessage;
import com.cfy.interestback.model.UserOperationMessage;
import com.cfy.interestback.service.OperationMessageService;
import com.cfy.interestback.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationMessageServiceImpl implements OperationMessageService {

    @Autowired
    private UserOperationMessageMapper userOperationMessageMapper;

    @Autowired
    private CircleOperationMessageMapper circleOperationMessageMapper;

    @Autowired
    private ArticleOperationMessageMapper articleOperationMessageMapper;


    @Override
    public List<UserOperationMessage> getuserList(SearchVo searchVo) {
        return userOperationMessageMapper.getList(searchVo);
    }

    @Override
    public List<CircleOperationMessage> getCircleList(SearchVo searchVo) {
        return circleOperationMessageMapper.getList(searchVo);
    }

    @Override
    public List<ArticleOperationMessage> getArticleList(SearchVo searchVo) {
        return articleOperationMessageMapper.getList(searchVo);
    }
}
