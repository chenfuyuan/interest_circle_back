package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.Circle;
import org.apache.ibatis.annotations.Select;

public interface CircleMapper extends BaseMapper<Circle> {

    @Select("select count(1) from circle where state = 1")
    Integer countNum();
}
