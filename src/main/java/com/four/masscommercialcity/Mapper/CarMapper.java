package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.Car;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface CarMapper {

    //添加商品到购物车
    @Insert("insert into car(pid,uid,num) values(#{pid},#{uid},#{num})")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    public int addProductToCar(Car car);

    //查询购物车
    @Select("select * from car")
    public ArrayList<Car> selectProductToCar();

    //更新购物车
    @Update("update car set num = #{num} where id = #{id}")
    public int updateProductToCar(Car car);

    //根据用户id获取购物车
    @Select("select * from car where uid = #{uid}")
    public ArrayList<Car> getCar(Integer id);

    //根据car的id删除car的信息
    @Delete("delete from car where id = #{id}")
    public int delProductToCar(Integer id);

    //根据car的用户id以及商品id删除car的信息
    @Delete("delete from car where uid = #{uid} and pid = #{pid}")
    public int delCar(@Param("uid") Integer uid, @Param("pid") Integer pid);
}
