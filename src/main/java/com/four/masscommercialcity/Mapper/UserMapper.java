package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface UserMapper {

    //查看所有用户
    @Select("select * from user")
    public ArrayList<User> selectUser();
}
