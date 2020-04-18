package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.ArticleReportMapper;
import com.cfy.interestback.model.ArticleReport;
import com.cfy.interestback.service.ArticleReportService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleReportServiceImpl implements ArticleReportService {

    @Autowired
    private ArticleReportMapper mapper;
    @Override
    public List<ArticleReport> getList(SearchVo searchVo) {
        return mapper.getList(searchVo);
    }

    @Override
    public List<ArticleReport> getDelList(SearchVo searchVo) {
        return mapper.getDealList(searchVo);
    }

    @Override
    public AjaxMessage add(ArticleReport articleReport) throws Exception {
        return null;
    }

    @Override
    public AjaxMessage update(ArticleReport articleReport) throws Exception {
        return null;
    }

    @Override
    public List<ArticleReport> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<ArticleReport> getDelListBySearch(String search) {
        return null;
    }

    @Override
    public AjaxMessage delete(Integer id) throws Exception {
        return null;
    }

    @Override
    public Integer deleteMore(Integer[] deleteList) {
        return null;
    }
}
