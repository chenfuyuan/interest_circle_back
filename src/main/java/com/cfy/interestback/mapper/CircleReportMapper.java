package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.CircleReport;
import org.apache.ibatis.annotations.Select;

public interface CircleReportMapper extends BaseMapper<CircleReport> {

    @Select("select count(1) from circle_report")
    Integer countNum();
}
