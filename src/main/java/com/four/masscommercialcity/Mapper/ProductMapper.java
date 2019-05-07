package com.four.masscommercialcity.Mapper;

import com.four.masscommercialcity.bean.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

public interface ProductMapper {


    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "pid")
    @Insert("insert into product (pname,market_price,Shop_price,pimage,pdate,Is_hot,pdesc,pflag,cid,num) " +
            "values(#{pname},#{market_price},#{Shop_price},#{pimage},#{pdate},#{Is_hot},#{pdesc},#{pflag},#{category.cid},#{num})")
    public int insert(Product product);


    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from product")
    @Results(value = {
            @Result(property = "category", column = "cid"
                    , one = @One(select = "com.four.masscommercialcity.Mapper.CategoryMapper.getCategory"))
    }, id = "productMap")
    public ArrayList<Product> all();


    /**
     * 删除商品
     *
     * @param pid
     * @return
     */
    @Delete("delete from product where pid = #{pid}")
    public int del(Integer pid);


    /**
     * 根据id查询商品
     *
     * @param pid
     * @return
     */
    @ResultMap("productMap")
    @Select("select * from product where pid = #{pid}")
    public Product getProduct(Integer pid);


    /**
     * 商品更新
     *
     * @param product
     * @return
     */
    @Update("update product set num = #{num},pname =#{pname},market_price=#{market_price}" +
            ",Shop_price=#{Shop_price},pdate=#{pdate},Is_hot=#{Is_hot},pdesc=#{pdesc},pflag=#{pflag},cid=#{category.cid} where pid =#{pid}")
    public int update(Product product);


    /**
     * 商品上下架
     *
     * @param pid
     * @param pflag
     * @return
     */
    @Update("update product set pflag = #{pflag} where pid = #{pid}")
    public int upOrdown(@Param("pid") Integer pid, @Param("pflag") Integer pflag);


    /**
     * 获取所有上架商品
     *
     * @return
     */
    @ResultMap("productMap")
    @Select("select * from product where pflag=0")
    public ArrayList<Product> productList();

    /**
     * 获取最新商品
     *
     * @return
     */
    @ResultMap("productMap")
    @Select("select * from product where  pflag = 0")
    public ArrayList<Product> productDate();

    /**
     * 获取最热商品
     *
     * @return
     */
    @ResultMap("productMap")
    @Select("select * from product where Is_hot = 1 and pflag = 0")
    public ArrayList<Product> productHot();

    /**
     * 根据分类查找所有商品
     *
     * @param cid
     * @return
     */
    @ResultMap("productMap")
    @Select("select * from product where cid=#{cid} and pflag = 0")
    public ArrayList<Product> productCategory(Integer cid);

}
