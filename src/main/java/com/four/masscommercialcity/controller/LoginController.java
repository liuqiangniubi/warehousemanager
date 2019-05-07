package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.CarMapper;
import com.four.masscommercialcity.Mapper.CategoryMapper;
import com.four.masscommercialcity.Mapper.ProductMapper;
import com.four.masscommercialcity.Mapper.UserMapper;
import com.four.masscommercialcity.bean.Car;
import com.four.masscommercialcity.bean.Category;
import com.four.masscommercialcity.bean.Product;
import com.four.masscommercialcity.bean.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CarMapper carMapper;

    /**
     * 初始化页面
     *
     * @param maps
     * @return
     */
    @GetMapping(value = {"/", "/index.html"})
    public String all(Map<String, Object> maps, HttpSession session) {
        ArrayList<Category> lists = categoryMapper.all();
        session.setAttribute("lists", lists);

        String orderBy = "pdate " + "desc";
        PageHelper.startPage(1, 4, orderBy);
        ArrayList<Product> productDateList = productMapper.productDate();
        maps.put("productDateList", productDateList);

        PageHelper.startPage(1, 4);
        ArrayList<Product> productHotList = productMapper.productHot();
        maps.put("productHotList", productHotList);
        return "index";
    }

    /**
     * 查看所有商品
     *
     * @return
     */
    @GetMapping(value = {"/user/selectProduct", "/user/selectProduct/{currentPage}"})
    public String products(Map<String, Object> maps,
                           @PathVariable(required = false, value = "currentPage") Integer currentPage
            , @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        PageInfo<Product> pageInfo = new PageInfo<Product>(productMapper.productList());
        maps.put("products", productMapper.productList());
        maps.put("pages", pageInfo);
        return "delivery";
    }

    /**
     * 分类查询商品
     *
     * @param currentPage
     * @param pageSize
     * @param cid
     * @param maps
     * @return
     */
    @GetMapping(value = {"/user/categoryProduct/{category}",
            "/user/categoryProduct/{currentPage}/{category}"})
    public String categoryProducts(@PathVariable(required = false, value = "currentPage") Integer currentPage,
                                   @RequestParam(defaultValue = "5", required = false) Integer pageSize,
                                   @PathVariable(value = "category", required = false) Integer cid,
                                   Map<String, Object> maps) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        ArrayList<Product> list = productMapper.productCategory(cid);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        maps.put("category", categoryMapper.getCategory(cid));
        maps.put("products", list);
        maps.put("pages", pageInfo);
        return "delivery";
    }

    /**
     * 查看商品详情
     *
     * @param pid
     * @param maps
     * @return
     */
    @GetMapping("/user/productDetails/{pid}")
    public String productDetails(@PathVariable("pid") Integer pid, Map<String, Object> maps) {
        Product product = productMapper.getProduct(pid);
        PageHelper.startPage(1, 4);
        ArrayList<Product> relatedProducts = productMapper.productCategory(product.getCategory().getCid());
        maps.put("productDetails", product);
        maps.put("relatedProducts", relatedProducts);
        return "car/preview";
    }

    /**
     * 处理get请求，用于跳转登录页面
     *
     * @return
     */
    @GetMapping(value = {"/user/login", "/user/login/{msg}"})
    public String loginPage(@PathVariable(value = "msg", required = false) String msg,
                            HttpServletRequest request) {
        if (msg != null) {
            request.setAttribute("msg", msg);
        }
        return "login/login";
    }

    /**
     * 处理post请求，用于用户登录
     *
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/user/login")
    public String login(String username, String password,
                        Model model, HttpSession session) {
        ArrayList<User> users = userMapper.selectUser();
        boolean bl = false;
        User u = null;
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                bl = true;
                u = user;
            }
        }
        if (bl) {
            session.setAttribute("user", u);
            ArrayList<Car> cars = carMapper.getCar(u.getUid());
            //计算商品数量
            // 计算价格
            double sum = 0;
            Integer size = 0;
            for (Car car : cars) {
                System.out.println(car);
                size += car.getNum();
                sum += productMapper.getProduct(car.getPid()).getShop_price() * car.getNum();
            }
            //将购物车放入session中
            session.setAttribute("prices", sum);
            session.setAttribute("items", size);
            return "redirect:/index.html";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "login/login";
        }
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @GetMapping("/user/out")
    public String out(HttpSession session) {
        session.setAttribute("user", null);
        session.setAttribute("prices", null);
        session.setAttribute("items", null);
        return "redirect:/index.html";
    }

    /**
     * 管理登录
     *
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/work/login")
    public String backstageLogin(String username, String password,
                                 Model model, HttpSession session) {
        if ("admin".equals(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return "dashboard";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "login/workLogin";
        }
    }

    /**
     * 管理退出
     *
     * @param session
     * @return
     */
    @GetMapping("/work/out")
    public String workOut(HttpSession session) {
        session.setAttribute("loginUser", null);
        return "redirect:/work";

    }
}
