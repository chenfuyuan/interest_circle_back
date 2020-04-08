package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Circle;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CircleMapper extends BaseMapper<Circle> {

    @Select("select count(1) from circle where state = 1")
    Integer countNum();

    @Select("select * from circle where state != 0")
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
    List<Circle> getList();

    @Select("select * from circle where state = 0 ")
    @ResultMap("circleMap")
    List<Circle> getDelList();


}
