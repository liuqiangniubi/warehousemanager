package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface OrderMapper {

    //添加订单
    @Insert("insert into orders(orderNum,ordertime,total,state,address,name,telepho,uid) values(#{orderNum},#{ordertime}," +
            "#{total},#{state},#{address},#{name},#{telepho},#{uid})")
    @Options(keyProperty = "oid", useGeneratedKeys = true)
    int addOrder(Orders order);

    //查询订单
    @Select("select * from orders")
    ArrayList<Orders> selectOrder();
}
