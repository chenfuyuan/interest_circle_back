package com.cfy.interestback.service.impl;

import com.cfy.interestback.mapper.ArticleMapper;
import com.cfy.interestback.model.Article;
import com.cfy.interestback.service.ArticleService;
import com.cfy.interestback.vo.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;



    @Override
    public List<Article> getList(Integer cid) {
        return mapper.getListByCid(cid);
    }

    @Override
    public List<Article> getDelList() {
        return mapper.getDelList();
    }


    @Override
    public List<Article> getDelList(Integer cid) {
        return mapper.getDelList(cid);
    }

    @Override
    public List<Article> getList() {
        return mapper.getList();
    }


    @Override
    public AjaxMessage add(Article article) throws Exception {
        return null;
    }

    @Override
    public AjaxMessage update(Article article) throws Exception {
        return null;
    }

    @Override
    public List<Article> getListBySearch(String search) {
        return null;
    }

    @Override
    public List<Article> getDelListBySearch(String search) {
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
