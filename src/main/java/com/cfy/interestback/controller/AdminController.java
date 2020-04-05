package com.cfy.interestback.controller;

import com.cfy.interestback.model.Admin;
import com.cfy.interestback.service.AdminService;
import com.cfy.interestback.vo.AjaxMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

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
        admin = adminService.login(admin);
        log.info("check admin = " + admin);
        if (admin == null) {
            return new AjaxMessage(false,"登录失败");
        }

        request.getSession().setAttribute("admin",admin);
        return new AjaxMessage(true,"登录成功");
    }
}
