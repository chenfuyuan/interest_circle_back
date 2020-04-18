package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.CircleReport;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CircleReportMapper extends BaseMapper<CircleReport> {

    @Select("select count(1) from circle_report")
    Integer countNum();

    @Select("<script> " +
            "select * from circle_report where state=1 " +
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
            @Result(property = "cid", column = "cid"),
            @Result(property = "user", column = "uid", one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(property = "circle", column = "cid", one = @One(select = "com.cfy.interestback.mapper" +
                    ".CircleMapper.selectById"))
    })
    List<CircleReport> getList(@Param("searchVo") SearchVo searchVo);

    @Select("<script> " +
            "select * from circle_report where state!=1 " +
            "<if test=\"searchVo.start!= null\"> and create_time &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and create_time &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (uid in (select id from user where name like \'%${searchVo.search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search})" +
            "</if>" +
            "</script>")
    @ResultMap("reportMap")
    List<CircleReport> getDelList(@Param("searchVo") SearchVo searchVo);

    @Update("update circle_report set state = 2 where cid = #{cid} and state = 1")
    int dealALLByCid(Integer cid);

    @Update("update circle_report set state = 3 where id = #{id} and state = 1")
    int cancel(Integer id);
}
