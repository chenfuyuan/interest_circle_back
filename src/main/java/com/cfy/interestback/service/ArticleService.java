package com.cfy.interestback.service;

import com.cfy.interestback.model.Article;

import java.util.List;


public interface ArticleService extends CommonService<Article> {

    List<Article> getList(Integer cid);

    List<Article> getDelList(Integer cid);
}
