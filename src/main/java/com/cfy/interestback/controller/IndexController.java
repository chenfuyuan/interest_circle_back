package com.cfy.interestback.controller;

import com.cfy.interestback.service.IndexService;
import com.cfy.interestback.vo.StatisticsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private IndexService indexService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/get/index/welcome")
    public String weclome(Model model) {
        Date nowTime = new Date();
        log.info("时间 = " + nowTime);
        model.addAttribute("nowTime", nowTime);

        //获取统计数据
        StatisticsMessage statistics = indexService.getStatistics();

        //传递数据
        model.addAttribute("statistics", statistics);

        return "welcome";
    }


}
