package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.CategoryMapper;
import com.four.masscommercialcity.Mapper.ProductMapper;
import com.four.masscommercialcity.bean.Category;
import com.four.masscommercialcity.bean.Product;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 跳转页面
     *
     * @return
     */
    @GetMapping(value = {"/product/allPage", "/product/allPage/{currentPage}"})
    public String AllPage(Map<String, Object> maps,
                          @PathVariable(required = false, value = "currentPage") Integer currentPage
            , @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        ArrayList<Product> list = productMapper.all();
//        for (Product product : list) {
//            System.out.println(product);
//        }
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        maps.put("pages", pageInfo);
        maps.put("lists", list);
        return "product/productList";
    }

    /**
     * 跳转add页面/回显页面
     *
     * @return
     */
    @GetMapping(value = {"/product", "/product/{pid}"})
    public String addPage(Map<String, Object> maps
            , @PathVariable(value = "pid", required = false) Integer pid) {
        ArrayList<Category> list = categoryMapper.all();
        maps.put("categorys", list);
        if (pid != null) {
            Product product = productMapper.getProduct(pid);
            maps.put("product", product);
        }
        return "/product/add";
    }

    /**
     * 添加商品
     *
     * @param product
     * @param file
     * @return
     */
    @PostMapping("/product")
    public String add(Product product, @RequestParam("file") MultipartFile file
            , HttpSession session, Integer cid) {
        System.out.println(product);
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();//获取文件名
//            System.out.println("文件名:"+originalFilename);
            //设置新文件名
            String newName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //设置文件存储路径
            String filePath = "E:\\img\\";
            String path = filePath + newName;

            File f = new File(path);
            //检查目录是否存在
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();//新建文件夹
            }
            try {
                file.transferTo(f);//文件写入
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setPimage(newName);
            product.setCategory(categoryMapper.getCategory(cid));
            productMapper.insert(product);
        }
        return "redirect:/product/allPage";
    }

    /**
     * 删除商品
     *
     * @param pid
     * @return
     */
    @DeleteMapping("/product/{pid}")
    public String del(@PathVariable("pid") Integer pid) {
        int result = productMapper.del(pid);
        return "redirect:/product/allPage";
    }

    /**
     * 修改商品
     *
     * @param product
     * @return
     */
    @PutMapping("/product")
    public String update(Product product, Integer cid) {
        product.setCategory(categoryMapper.getCategory(cid));
//        System.out.println(product);
        int result = productMapper.update(product);
        return "redirect:/product/allPage";
    }

    /**
     * 商品上下架
     *
     * @param pid
     * @param pflag
     * @return
     */
    @GetMapping(value = "/product/{pid}/{p}")
    public String upOrdown(@PathVariable("pid") Integer pid, @PathVariable("p") Integer pflag) {
        System.out.println("商品id:" + pid + "，是否热门:" + pflag);
        if (pflag == 0) {
            pflag = 1;
        } else {
            pflag = 0;
        }
        int result = productMapper.upOrdown(pid, pflag);
        return "redirect:/product/allPage";
    }
}
