package com.cfy.interestback.controller;

import com.cfy.interestback.model.Article;
import com.cfy.interestback.service.ArticleService;
import com.cfy.interestback.vo.SearchVo;
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
public class ArticleController {

    @Autowired
    private ArticleService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/articleList")
    public String getList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Article> list = service.getList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<Article>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "article-list";
    }

    @GetMapping("/get/index/articleListByCid")
    public String getListByCid(@RequestParam("pageNum")Integer pageNum, @RequestParam("cid") Integer cid,Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<Article> list = service.getList(cid);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<Article>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "article-list";
    }

    @GetMapping("/get/index/articleDel")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Article> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "article-del";
    }

    @GetMapping("/get/index/articleDelByCid")
    public String getDelListByCid(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("cid")Integer cid, Model model) {
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<Article> list = service.getDelList(cid);
            log.info("list = " +list);
            //封装分页
            PageInfo<Article> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "article-del";
    }
}
