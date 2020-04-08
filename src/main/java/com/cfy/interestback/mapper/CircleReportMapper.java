package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.CircleReport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CircleReportMapper extends BaseMapper<CircleReport> {

    @Select("select count(1) from circle_report")
    Integer countNum();

    @Select("select * from circle_report where state=1")
    List<CircleReport> getList();

    @Select("select * from circle_report where state=2")
    List<CircleReport> getDelList();
}
