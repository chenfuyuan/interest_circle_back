package com.cfy.interestback.vo;

import lombok.Data;

@Data
public class SearchVo {

    private Integer pageNum;
    private String start;
    private String end;
    private String search;
}
