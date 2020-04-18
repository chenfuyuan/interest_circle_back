package com.cfy.interestback.controller;

import com.cfy.interestback.model.CircleReport;
import com.cfy.interestback.service.CircleReportService;
import com.cfy.interestback.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class CircleReportController {

    @Autowired
    private CircleReportService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/circleReportList")
    public String getList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<CircleReport> list = service.getList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<CircleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);

            String start = searchVo.getStart();
            String end = searchVo.getEnd();
            String search = searchVo.getSearch();

            String paramsUrl = "&";
            Integer paramsNum = 0;
            if (start != null && !start.equals("")) {

                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "start=" + start;
                model.addAttribute("start", start);
                paramsNum++;
            }

            if (end != null && !end.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "end="+end;
                model.addAttribute("end", end);
                paramsNum++;
            }
            if (search != null && !search.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "search="+search;
                paramsNum++;
                model.addAttribute("search", search);
            }

            if (paramsNum != 0) {
                model.addAttribute("paramUrl", paramsUrl);
            }
        }finally {
            PageHelper.clearPage();
        }
        return "circle-report-list";
    }

    @GetMapping("/get/index/circleReportDeal")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<CircleReport> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<CircleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);

            String start = searchVo.getStart();
            String end = searchVo.getEnd();
            String search = searchVo.getSearch();

            String paramsUrl = "&";
            Integer paramsNum = 0;
            if (start != null && !start.equals("")) {

                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "start=" + start;
                model.addAttribute("start", start);
                paramsNum++;
            }

            if (end != null && !end.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "end="+end;
                model.addAttribute("end", end);
                paramsNum++;
            }
            if (search != null && !search.equals("")) {
                if (paramsNum != 0) {
                    paramsUrl += "&";
                }
                paramsUrl += "search="+search;
                paramsNum++;
                model.addAttribute("search", search);
            }

            if (paramsNum != 0) {
                model.addAttribute("paramUrl", paramsUrl);
            }
        }finally {
            PageHelper.clearPage();
        }
        return "circle-report-deal";
    }
}
