package com.four.masscommercialcity.componet;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登入检查
 */
public class MyInterceptor implements HandlerInterceptor {
    //方法执行前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            //未登入
            request.setAttribute("msg", "权限不足请登入");
            request.getRequestDispatcher("/work").forward(request, response);
            return false;
        } else {
            //已登入
            return true;
        }
    }
}
