package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface AddressMapper {

    //添加地址
    @Insert("insert into address(province,city,district,address,name,phone,uid) values(#{province},#{city},#{district},#{address},#{name},#{phone},#{uid})")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    int addAddress(Address address);

    //根据用户查看地址
    @Select("select * from address where uid = #{uid}")
    ArrayList<Address> selectAddress(Integer uid);

    //根据id查找地址
    @Select("select * from address where id = #{id}")
    Address getAddress(Integer id);
}
