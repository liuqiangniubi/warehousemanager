package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.CategoryMapper;
import com.four.masscommercialcity.bean.Category;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class CategoryController {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 页面跳转,并显示所有
     *
     * @return
     */
    @RequestMapping(value = {"/category/allPage", "/category/allPage/{currentPage}"})
    public String allPage(Map<String, Object> maps,
                          @PathVariable(required = false, value = "currentPage") Integer currentPage
            , @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        ArrayList<Category> lists = categoryMapper.all();//获取所有分类
        PageInfo<Category> pageInfo = new PageInfo<Category>(lists);
        maps.put("pages", pageInfo);
        maps.put("lists", lists);
        return "category/categoryList";
    }

    /**
     * 跳转add页面
     *
     * @return
     */
    @GetMapping("/categorys")
    public String addPage() {
        return "/category/add";
    }

    /**
     * 添加分类
     *
     * @param category
     * @return
     */
    @PostMapping("/categorys")
    public String add(Category category) {
        int result = categoryMapper.insert(category);
        return "redirect:/category/allPage";
    }

    /**
     * 页面回显
     *
     * @param uid
     * @param model
     * @return
     */
    @GetMapping("/categorys/{uid}")
    public String edit(@PathVariable("uid") Integer uid, Model model) {
        Category category = categoryMapper.getCategory(uid);
        model.addAttribute("category", category);

        return "/category/add";
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @PutMapping("/categorys")
    public String update(Category category) {
        int result = categoryMapper.update(category);
        return "redirect:/category/allPage";
    }

    /**
     * 删除分类
     *
     * @param cid
     * @return
     */
    @DeleteMapping("/categorys/{cid}")
    public String delete(@PathVariable("cid") Integer cid) {
        int result = categoryMapper.delete(cid);
        return "redirect:/category/allPage";
    }
}
