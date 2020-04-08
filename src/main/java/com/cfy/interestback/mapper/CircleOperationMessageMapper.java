package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.CircleOperationMessage;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CircleOperationMessageMapper extends BaseMapper<CircleOperationMessage> {

    @Select("<script> " +
            "select * from circle_operation_message where 1 " +
            "<if test=\"searchVo.start!= null\"> and datetime &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and datetime &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (u_id in (select id from user where name like \'%${searchVo" +
            ".search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search} or message like \'%${searchVo.search}%\')" +
            "</if>"+
            "</script>")
    @Results(id = "userOperationMessageMap", value = {
            @Result(column = "u_id", property = "uId"),
            @Result(column = "u_id", property = "user", one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById")),
            @Result(column = "c_id", property = "cId"),
            @Result(column = "c_id", property = "circle", one = @One(select =
                    "com.cfy.interestback.mapper.CircleMapper" +
                            ".selectById"))
    })
    List<CircleOperationMessage> getList(@Param("searchVo") SearchVo searchVo);
}
