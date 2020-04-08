package com.cfy.interestback.service;

import com.cfy.interestback.model.ArticleOperationMessage;
import com.cfy.interestback.model.CircleOperationMessage;
import com.cfy.interestback.model.UserOperationMessage;
import com.cfy.interestback.vo.SearchVo;

import java.util.List;

public interface OperationMessageService {

    List<UserOperationMessage> getuserList(SearchVo searchVo);

    List<CircleOperationMessage> getCircleList(SearchVo searchVo);

    List<ArticleOperationMessage> getArticleList(SearchVo searchVo);
}
