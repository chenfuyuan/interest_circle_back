package com.cfy.interestback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleStar;
import org.apache.ibatis.annotations.Update;

public interface ArticleStarMapper extends BaseMapper<ArticleStar> {


    @Update("update article_star set state = 0 where state = 1 and aid =#{aid}")
    int cancelStarByAid(Integer aid);

}
