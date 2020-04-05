package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Article;
import org.apache.ibatis.annotations.Select;

public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select count(1) from article where state = 1")
    Integer countNum();
}
