package com.cfy.interestback.config.intercepors;

import com.cfy.interestback.model.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl =
                url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/get/index/login").toString();
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        Admin admin =  (Admin)session.getAttribute("admin");
        //如果session中没有user，表示没登陆
        log.info("path = "+request.getRequestURL());
        log.info("session 中的admin = " + admin);
        if (admin == null) {
            log.info("跳转到"+tempContextUrl);
            response.sendRedirect(tempContextUrl);
            return false;
        } else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}


