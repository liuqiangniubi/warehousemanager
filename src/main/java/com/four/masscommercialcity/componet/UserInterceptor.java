package com.four.masscommercialcity.componet;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登入检查
 */
public class UserInterceptor implements HandlerInterceptor {
    //方法执行前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            //未登入
            request.setAttribute("msg", "请登入");
            request.getRequestDispatcher("/user/login").forward(request, response);
            return false;
        } else {
            //已登入
            return true;
        }
    }
}
