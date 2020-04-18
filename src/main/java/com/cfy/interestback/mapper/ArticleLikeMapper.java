package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleLike;
import org.apache.ibatis.annotations.Update;

public interface ArticleLikeMapper extends BaseMapper<ArticleLike> {


    @Update("update article_like set state = 0 where aid = #{aid} and state = 1")
    int cancelLikeByAid(Integer aid);



}
