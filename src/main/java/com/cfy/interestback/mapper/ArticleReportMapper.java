package com.cfy.interestback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfy.interestback.model.ArticleReport;
import org.apache.ibatis.annotations.Select;

public interface ArticleReportMapper extends BaseMapper<ArticleReport> {

    @Select("select count(1) from article_report")
    Integer countNum();
}
