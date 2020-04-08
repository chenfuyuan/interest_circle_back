package com.cfy.interestback.controller;

import com.cfy.interestback.model.ArticleOperationMessage;
import com.cfy.interestback.model.CircleOperationMessage;
import com.cfy.interestback.model.UserOperationMessage;
import com.cfy.interestback.service.OperationMessageService;
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
public class OperationMessageController {

    @Autowired
    private OperationMessageService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/userOperationMessage")
    public String getUserList(SearchVo searchVo,
                              Model model) {
        log.info("searchVo = " +searchVo);
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<UserOperationMessage> list = service.getuserList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<UserOperationMessage> pageInfo = new PageInfo<>(list, pageSize);

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
        return "userOperationMessage";
    }

    @GetMapping("/get/index/circleOperationMessage")
    public String getCircleList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<CircleOperationMessage> list = service.getCircleList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<CircleOperationMessage> pageInfo = new PageInfo<>(list, pageSize);

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
        return "circleOperationMessage";
    }

    @GetMapping("/get/index/articleOperationMessage")
    public String getArticleList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<ArticleOperationMessage> list = service.getArticleList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<ArticleOperationMessage> pageInfo = new PageInfo<>(list, pageSize);

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
        return "articleOperationMessage";
    }


}
