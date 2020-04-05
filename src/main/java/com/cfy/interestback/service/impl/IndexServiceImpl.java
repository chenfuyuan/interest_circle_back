package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.*;
import com.cfy.interestback.service.IndexService;
import com.cfy.interestback.vo.StatisticsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CircleMapper circleMapper;

    @Autowired
    private ArticleReportMapper articleReportMapper;

    @Autowired
    private CircleReportMapper circleReportMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;
    @Override
    public StatisticsMessage getStatistics() {
        StatisticsMessage statisticsMessage = new StatisticsMessage();
        //获取用户数
        statisticsMessage.setUserNum(userMapper.countNum());

        //获取圈子数
        statisticsMessage.setCircleNum(circleMapper.countNum());

        //获取帖子数
        statisticsMessage.setArticleNum(articleMapper.countNum());

        //获取圈子举报数
        statisticsMessage.setCircleReportNum(circleReportMapper.countNum());

        //获取帖子举报数
        statisticsMessage.setArticleReportNum(articleReportMapper.countNum());

        //获取评论数
        statisticsMessage.setCommentNum(articleCommentMapper.countNum());

        return statisticsMessage;
    }
}
