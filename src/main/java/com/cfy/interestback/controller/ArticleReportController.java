package com.cfy.interestback.controller;

import com.cfy.interestback.model.ArticleReport;
import com.cfy.interestback.service.ArticleReportService;
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
public class ArticleReportController {

    @Autowired
    private ArticleReportService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/articleReport")
    public String getList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<ArticleReport> list = service.getList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<ArticleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "articleReport-list";
    }

    @GetMapping("/get/index/articleReportDeal")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<ArticleReport> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<ArticleReport> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "articleReport-del";
    }
}
