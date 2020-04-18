package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Article;
import com.cfy.interestback.vo.SearchVo;
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

    @Select("<script> " +
            "select * from article where state!=0 " +
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
    @ResultMap("articleMap")
    List<Article> getList(@Param("searchVo") SearchVo searchVo);

    @Select("select * from article where state = 0 and cid = #{cid}")
    @ResultMap("articleMap")
    List<Article> getDelListByCid(Integer cid);

    @Select("<script> " +
            "select * from article where state=0 " +
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
    @ResultMap("articleMap")
    List<Article> getDelList(@Param("searchVo") SearchVo searchVo);

    @Update({
            "<script>"
                    + "update article set state = 0 where state !=0 and id in "
                    + "<foreach item = 'item' index = 'index' collection = 'id' open='(' separator = ',' close=')'>"
                    + "#{item}"
                    +"</foreach>"
                    +"</script>"
    })
    Integer deleteMore(@Param("id") Integer[] deleteList);

    @Select("<script>select id from article_comment where aid in <foreach item='item' index = 'index' " +
            "collection='ids' open='" +
            "('" +
            " separator=',' close=')'>" +
            "  #{item}" +
            "</foreach>" +
            "</script>" +
            " ")
    List<Integer> selectCommentByAids(@Param("ids")Integer[] deleteList);
    @Update({
            "<script>"
                    + "update article_comment set state = 0 where state !=0 and aid in "
                    + "<foreach item = 'item' index = 'index' collection = 'ids' open='(' separator = ',' close=')'>"
                    + "#{item}"
                    +"</foreach>"
                    +"</script>"
    })
    Integer deleteCommentByAids(@Param("ids") Integer[] deleteList);

    @Update({
            "<script>"
                    + "update article_comment_reply set state = 0 where state !=0 and acid in "
                    + "<foreach item = 'item' index = 'index' collection = 'ids' open='(' separator = ',' close=')'>"
                    + "#{item}"
                    +"</foreach>"
                    +"</script>"
    })
    Integer deleteReplyByRuid(@Param("ids") List<Integer> commnetIdLit);

    @Select("select * from article where id = #{id}")
    @ResultMap("articleMap")
    Article getArticleDetail(Integer id);

    @Update("update article set comment_num = comment_num - #{num} where id = #{aid} and state!=0")
    int deleteComment(Integer aid, int num);


    @Update("update article set state = 0 where id = #{aid}")
    int deleteByAid(Integer aid);
}
