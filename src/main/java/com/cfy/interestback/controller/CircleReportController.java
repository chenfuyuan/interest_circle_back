package com.cfy.interestback.controller;

import com.cfy.interestback.model.CircleReport;
import com.cfy.interestback.service.CircleReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class CircleReportController {

    @Autowired
    private CircleReportService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/circleReport")
    public String getList(@RequestParam("pageNum")Integer pageNum, Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<CircleReport> list = service.getList();
            log.info("list = " +list);
            //封装分页
            PageInfo<CircleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "circleReport-list";
    }

    @GetMapping("/get/index/circlReportDeal")
    public String getDelList(@RequestParam("pageNum") Integer pageNum, Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<CircleReport> list = service.getDelList();
            log.info("list = " +list);
            //封装分页
            PageInfo<CircleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "circleReport-deal";
    }
}
