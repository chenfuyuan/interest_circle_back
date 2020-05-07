package com.cfy.interestback.controller;

import com.cfy.interestback.model.Article;
import com.cfy.interestback.model.Circle;
import com.cfy.interestback.service.CircleService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class CircleController {

    @Autowired
    private CircleService service;

    private Integer num = 4;

    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/circleList")
    public String getList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Circle> list = service.getList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Circle> pageInfo = new PageInfo<>(list, pageSize);

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
        return "circle-list";
    }

    @GetMapping("/get/index/circleDel")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<Circle> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<Circle> pageInfo = new PageInfo<>(list, pageSize);

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
        return "circle-del";
    }

    @PostMapping("/circle/delete/more")
    @ResponseBody
    public AjaxMessage deleteMore(@RequestParam(value = "ids[]") Integer[] deleteList) {
        log.info("deleteList = " + Arrays.toString(deleteList));

        //删除用户
        Integer integer = null;
        try {
            integer = service.deleteMore(deleteList);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, "删除失败");

        }

        log.info("删除了" + integer + "个圈子");
            return new AjaxMessage(true, "删除成功");


    }


    @GetMapping("/get/circle/detail")
    public String getCircleDetail(Integer cid, Model model) {
        //获取圈子信息
        Circle circle = service.getCircleByCid(cid);
        //获取圈子成员头像

        List<String> memberAvatars = service.getMemberAvatars(cid,num);
        //获取置顶栏
        List<Article> stickys = service.getStickys(cid);

        //传递属性
        model.addAttribute("circle", circle);
        log.info("当前圈子为："+circle);
        model.addAttribute("avatarPaths", memberAvatars);
        log.info("当前圈子成员的部分头像路径为：" + memberAvatars);
        model.addAttribute("stickys", stickys);
        log.info("当前圈子的置顶栏为"+stickys);
        return "circle-detail";
    }

}
