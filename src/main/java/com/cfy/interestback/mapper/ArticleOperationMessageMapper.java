package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleOperationMessage;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleOperationMessageMapper extends BaseMapper<ArticleOperationMessage> {

    @Select("<script> " +
            "select id,uid,aid,type,cid,create_time from article_operation_message where 1 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (uid in (select id from user where name like \'%${searchVo.search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search} or message like \'%${searchVo.search}%\')" +
            "</if>"+
            "</script>")
    @Results(id = "userOperationMessageMap", value = {
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "user", one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(column = "cid", property = "cid"),
            @Result(column = "cid", property = "circle", one = @One(select =
                    "com.cfy.interestback.mapper.CircleMapper" +
                            ".selectById")),
            @Result(column = "aid", property = "aid"),
            @Result(column = "aid",property = "article",one = @One(select = "com.cfy.interestback.mapper" +
                    ".ArticleMapper.selectById"))
    })
    List<ArticleOperationMessage> getList(@Param("searchVo") SearchVo searchVo);
}
