package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.Orderitem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface OrderitemMapper {

    //添加订单详情
    @Insert("insert into orderitem(count,pid,oid) values(#{count},#{pid},#{oid})")
    int addOrderitem(Orderitem orderitem);

    //查看订单详情
    @Select("select * from orderitem where oid = #{oid}")
    ArrayList<Orderitem> selectOrderItem(Integer oid);
}
