package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.District;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface DistrictMapper extends BaseMapper<District> {

    @Select("select * from district where id = #{id}")
    @Results(
            {
                    @Result(property = "parentId", column = "parent_id"),
                    @Result(property = "parent",
                            column = "parent_id",
                            one = @One(select = "com.cfy.interestback.mapper.DistrictMapper.selectById"))
            }
    )
    public District selectById(int id);
}
