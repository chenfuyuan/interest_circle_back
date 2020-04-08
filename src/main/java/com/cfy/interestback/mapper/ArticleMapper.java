package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select count(1) from article where state = 1")
    Integer countNum();

    @Select("select * from article where state!=0 and cid=#{cid} ")
    @Results(id = "articleMap", value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "user", column = "uid", one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(property = "circle", column = "cid", one = @One(select = "com.cfy.interestback.mapper" +
                    ".CircleMapper.selectById"))
    })
    List<Article> getListByCid(Integer cid);

    @Select("select * from article where state!=0")
    @ResultMap("articleMap")
    List<Article> getList();

    @Select("select * from article where state = 0 and cid = #{cid}")
    @ResultMap("articleMap")
    List<Article> getDelList(Integer cid);

    @Select("select * from article where state = 0")
    @ResultMap("articleMap")
    List<Article> getDelList();
}
