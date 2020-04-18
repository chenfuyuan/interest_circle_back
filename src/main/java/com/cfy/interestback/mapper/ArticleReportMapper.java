package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleReport;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleReportMapper extends BaseMapper<ArticleReport> {

    @Select("select count(1) from article_report")
    Integer countNum();

    @Select("<script> " +
            "select * from article_report where state=1 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (uid in (select id from user where name like \'%${searchVo.search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search})" +
            "</if>"+
            "</script>")
    @Results(id = "reportMap", value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "aid", column = "aid"),
            @Result(property = "user", column = "uid", one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(property = "article", column = "aid", one = @One(select = "com.cfy.interestback.mapper" +
                    ".ArticleMapper.selectById"))
    })
    List<ArticleReport> getList(@Param("searchVo") SearchVo searchVo);


    @Select("<script> " +
            "select * from article_report where state!=1 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (uid in (select id from user where name like \'%${searchVo.search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search})" +
            "</if>"+
            "</script>")
    @ResultMap("reportMap")
    List<ArticleReport> getDealList(@Param("searchVo") SearchVo searchVo);

    @Update("update article_report set state = 2 where aid = #{aid} and state = 1")
    int dealALLByAid(Integer aid);

    @Update("update article_report set state = 3 where id = #{id} and state = 1")
    int cancel(Integer id);
}
