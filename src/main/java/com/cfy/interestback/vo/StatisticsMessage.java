package com.cfy.interestback.vo;

import lombok.Data;

@Data
public class StatisticsMessage {
    private Integer userNum;
    private Integer circleNum;
    private Integer articleNum;
    private Integer circleReportNum;
    private Integer articleReportNum;
    private Integer commentNum;
}
