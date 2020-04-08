package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.UserOperationMessage;
import com.cfy.interestback.vo.SearchVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserOperationMessageMapper extends BaseMapper<UserOperationMessage> {

    @Select("<script> " +
            "select * from user_operation_message where 1 " +
            "<if test=\"searchVo.start!= null\"> and datetime &gt; #{searchVo.start} </if>" +
            "<if test=\"searchVo.end!= null\"> and datetime &lt; #{searchVo.end} </if>" +
            "<if test=\"searchVo.search\"> and (uid in (select id from user where name like \'%${searchVo.search}%\')" +
            " " +
            "or " +
            "id =" +
            " " +
            "#{searchVo.search} or message like \'%${searchVo.search}%\')" +
            "</if>"+
            "</script>")
    @Results(id="userOperationMessageMap",value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "user",one = @One(select = "com.cfy.interestback.mapper.UserMapper" +
                    ".selectById"))
    })
    List<UserOperationMessage> getList(@Param("searchVo") SearchVo searchVo);

}
