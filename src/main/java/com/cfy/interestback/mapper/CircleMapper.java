package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CircleMapper extends BaseMapper<Circle> {

    @Select("select count(1) from circle where state = 1")
    Integer countNum();

    @Select("<script> " +
            "select * from circle where state!=0 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (owner_id in (select id from user where name like \'%${searchVo" +
            ".search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search})" +
            "</if>"+
            "</script>")
    @Results( id="circleMap",value = {
            @Result(property = "districtId", column = "district_id"),
            @Result(property = "district",
                    column = "district_id",
                    one = @One(select = "com.cfy.interestback.mapper.DistrictMapper.selectById")
            ),
            @Result(property = "ownerId", column = "owner_id"),
            @Result(property = "owner",
                    column = "owner_id",
                    one = @One(select = "com.cfy.interestback.mapper.UserMapper.selectById")
            )
    })
    List<Circle> getList(@Param("searchVo") SearchVo searchVo);

    @Select("<script> " +
            "select * from circle where state=0 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (owner_id in (select id from user where name like \'%${searchVo" +
            ".search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search})" +
            "</if>"+
            "</script>")
    @ResultMap("circleMap")
    List<Circle> getDelList(@Param("searchVo") SearchVo searchVo);


    @Update({
            "<script>"
                    + "update circle set state = 0 where state !=0 and id in "
                    + "<foreach item = 'item' index = 'index' collection = 'id' open='(' separator = ',' close=')'>"
                    + "#{item}"
                    +"</foreach>"
                    +"</script>"
    })
    Integer deleteMore(@Param("id") Integer[] deleteList);


    @Update({
            "<script>"
                    + "update article set state = 0 where state !=0 and cid in "
                    + "<foreach item = 'item' index = 'index' collection = 'cid' open='(' separator = ',' close=')'>"
                    + "#{item}"
                    +"</foreach>"
                    +"</script>"
    })
    Integer deleteArticleListByCid(@Param("cid") Integer[] deleteList);

    @Select("select avatar_Path from user where id in (select uid from circle_user where cid = #{cid}) limit 5")
    List<String> getCircleUserAvatarPath(Integer cid);

    @Update("update circle set article_num = article_num -1 where id = #{cid} and state !=0")
    int deleteArticle(Integer cid);

    @Select("select * from circle where id = #{cid} and state != 0")
    Circle getCircleByCid(Integer cid);

}
