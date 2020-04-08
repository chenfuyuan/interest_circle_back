package com.cfy.interestback.controller;

import com.cfy.interestback.model.Admin;
import com.cfy.interestback.service.AdminService;
import com.cfy.interestback.vo.AjaxMessage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    private AdminService service;

    @Value("${com.cfy.interest.pageSize}")
    private Integer pageSize;

    @GetMapping("/get/index/login")
    public String loginIndex(Model model) {
        model.addAttribute("admin", new Admin());
        return "login";
    }

    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:/";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxMessage login(@ModelAttribute("admin") Admin admin, HttpServletRequest request) {
        log.info("admin = " + admin);
        admin = service.login(admin);
        log.info("check admin = " + admin);
        if (admin == null) {
            return new AjaxMessage(false,"登录失败");
        }
        request.getSession().setAttribute("admin",admin);
        return new AjaxMessage(true,"登录成功");
    }

    @GetMapping("/get/index/adminList")
    public String getList(@RequestParam("pageNum")Integer pageNum, Model model,HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Integer adminId = admin.getId();
        //启动分页
        PageHelper.startPage(pageNum, pageSize);
        try {
            //查询数据
            List<Admin> list = service.getList(adminId);
            log.info("list = " +list);
            //封装分页
            PageInfo<Admin> pageInfo = new PageInfo<>(list, pageSize);

            model.addAttribute("pageInfo", pageInfo);

            log.info("pageSize = " + pageSize);
        }finally {
            PageHelper.clearPage();
        }
        return "admin-list";
    }


    @GetMapping("/get/index/adminAdd")
    public String indexAdminAdd() {
        return "admin-add";
    }

    @PostMapping("/admin/add")
    public AjaxMessage addAdmin() {
        return new AjaxMessage(true, "增加成功");
    }

}
