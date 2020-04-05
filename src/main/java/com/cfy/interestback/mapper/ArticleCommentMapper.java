package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleComment;
import org.apache.ibatis.annotations.Select;

public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    @Select("select count(1) from article_comment where state = 1 ")
    Integer countNum();
}
