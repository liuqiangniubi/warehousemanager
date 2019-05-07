package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.UserMapper;
import com.four.masscommercialcity.bean.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    /**
     * 查看用户
     *
     * @param maps
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = {"/users/allPage", "/users/allPage/{currentPage}"})
    public String allPage(Map<String, Object> maps, @PathVariable(required = false, value = "currentPage") Integer currentPage
            , @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        ArrayList<User> users = userMapper.selectUser();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        maps.put("users", users);
        maps.put("pages", pageInfo);
        return "/user/userList";
    }
}
