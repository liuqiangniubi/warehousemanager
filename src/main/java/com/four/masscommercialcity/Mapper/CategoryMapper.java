package com.four.masscommercialcity.Mapper;


import com.four.masscommercialcity.bean.Category;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

public interface CategoryMapper {

    /**
     * 查询所有分类
     *
     * @return
     */
    @Select("select * from category")
    public ArrayList<Category> all();

    /**
     * 增加分类
     *
     * @return
     */
    @Options(keyProperty = "cid", useGeneratedKeys = true)
    @Insert("insert into category(cname) values(#{cname})")
    public int insert(Category category);

    /**
     * 删除分类
     *
     * @param cid
     * @return
     */
    @Delete("delete from category where cid = #{cid}")
    public int delete(Integer cid);

    /**
     * 更新分类
     *
     * @param category
     * @return
     */
    @Update("update category set cname = #{cname} where cid = #{cid}")
    public int update(Category category);

    /**
     * 根据id查找分类
     *
     * @param uid
     * @return
     */
    @Select("select * from category where cid= #{cid}")
    public Category getCategory(Integer uid);
}
