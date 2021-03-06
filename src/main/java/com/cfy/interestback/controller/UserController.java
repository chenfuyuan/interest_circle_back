package com.cfy.interestback.controller;

import com.cfy.interestback.model.User;
import com.cfy.interestback.service.UserService;
import com.cfy.interestback.vo.AjaxMessage;
import com.cfy.interestback.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService service;


    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/userList")
    public String getList(SearchVo searchVo, Model model) {
        log.info("searchVo = " + searchVo);
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<User> list = service.getList(searchVo);
            log.info("list = " + list);
            //封装分页
            PageInfo<User> pageInfo = new PageInfo<>(list, pageSize);

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
        } finally {
            PageHelper.clearPage();
        }
        return "user-list";
    }

    @PostMapping("/user/delete/more")
    @ResponseBody
    public AjaxMessage deleteMore(@RequestParam(value = "ids[]") Integer[] deleteList) {
        Integer changRow = null;
        try {
            changRow = service.deleteMore(deleteList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("改变了"+changRow+"行");
        return new AjaxMessage(true, "删除成功");
    }


    @GetMapping("/user/stop/{id}")
    @ResponseBody
    public AjaxMessage stopUser(@PathVariable("id") Integer id) {
        log.info("要停用的id为" + id);
        try {
            return service.stop(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
    }

    @GetMapping("/user/star/{id}")
    @ResponseBody
    public AjaxMessage starUser(@PathVariable("id") Integer id) {
        log.info("要停用的id为" + id);
        try {
            return service.star(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
    }

    @GetMapping("/user/delete/{id}")
    @ResponseBody
    public AjaxMessage deleteUser(@PathVariable("id") Integer id) {
        log.info("要停用的id为" + id);
        try {
            return service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxMessage(false, e.getMessage());
        }
    }

    @GetMapping("/get/index/userDel")
    public String getDelList(SearchVo searchVo, Model model) {
        //启动分页
        PageHelper.startPage(searchVo.getPageNum(), pageSize);
        try {
            //查询数据
            List<User> list = service.getDelList(searchVo);
            log.info("list = " +list);
            //封装分页
            PageInfo<User> pageInfo = new PageInfo<>(list, pageSize);

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
        return "user-del";
    }
}
