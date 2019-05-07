package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.AddressMapper;
import com.four.masscommercialcity.Mapper.CarMapper;
import com.four.masscommercialcity.Mapper.ProductMapper;
import com.four.masscommercialcity.bean.Address;
import com.four.masscommercialcity.bean.Car;
import com.four.masscommercialcity.bean.Product;
import com.four.masscommercialcity.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CarController {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    AddressMapper addressMapper;

    /**
     * 添加购物车
     *
     * @param pid
     * @param session
     * @return
     */
    @GetMapping(value = {"/car/{pid}/{uid}", "/car/{pid}/{uid}/{num}"})
    @ResponseBody
    public Map<String, Object> addProductToCar(@PathVariable("pid") Integer pid
            , @PathVariable("uid") Integer uid, @PathVariable(value = "num", required = false) Integer num, HttpSession session) {
        if (num == null) {
            num = 1;
        }
        //查询购物车
        ArrayList<Car> cars = carMapper.getCar(uid);
        boolean bl = true;
        //遍历购物车
        Car c = null;
        for (Car car : cars) {
            if (car.getPid() == pid && car.getUid() == uid) {
                c = car;
                bl = false;
                System.out.println("相同商品:" + c);
            }
        }
        if (bl) {
            //添加
            Car car = new Car();
            car.setPid(pid);
            car.setUid(uid);
            car.setNum(num);
            carMapper.addProductToCar(car);
            cars.add(car);
        } else {
            c.setNum(c.getNum() + num);
            carMapper.updateProductToCar(c);
            System.out.println("添加数量后:" + c);
        }
        Map<String, Object> maps = new HashMap<>();
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
        maps.put("sum", sum);
        maps.put("size", size);
        maps.put("state", true);
        return maps;
    }

    /**
     * 删除购物车商品
     *
     * @param pid
     * @param session
     * @return
     */
    @GetMapping("/car/del/{pid}/{uid}")
    @ResponseBody
    public Map<String, Object> delProductToCar(@PathVariable("pid") Integer pid,
                                               @PathVariable("uid") Integer uid, HttpSession session) {
        Map<String, Object> maps = new HashMap<>();
        ArrayList<Car> cars = carMapper.getCar(uid);
        Car c = null;
        for (Car car1 : cars) {
            if (car1.getPid() == pid) {
                c = car1;
            }
        }
        if (c != null) {
            System.out.println("要删除的car:" + c);
            carMapper.delProductToCar(c.getId());
        }
        ArrayList<Car> cars2 = carMapper.getCar(uid);
        Map<Product, Integer> products = new HashMap<>();
        Integer size = 0;
        double sum = 0;
        for (Car car : cars2) {
            products.put(productMapper.getProduct(car.getPid()), car.getNum());
            size += car.getNum();
            sum += productMapper.getProduct(car.getPid()).getShop_price() * car.getNum();
        }
        session.setAttribute("products", cars2);
        session.setAttribute("prices", sum);
        session.setAttribute("items", size);

        maps.put("sum", sum);
        maps.put("size", size);
        maps.put("state", true);
        return maps;
    }

    /**
     * 数量加减
     *
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @GetMapping("/car/num/{pid}/{uid}/{num}")
    @ResponseBody
    public Map<String, Object> numProductToCar(@PathVariable("pid") Integer pid
            , @PathVariable("num") Integer num, @PathVariable("uid") Integer uid, HttpSession session) {
        Car car = null;
        boolean bl = false;
        ArrayList<Car> cars = carMapper.getCar(uid);
        for (Car c : cars) {
            if (c.getPid() == pid) {
                System.out.println("得到前的car:" + c);
                c.setNum(num);
                car = c;
                bl = true;
                System.out.println("得到的car:" + car);
            }
        }
        if (bl) {
            carMapper.updateProductToCar(car);
        }
        Map<String, Object> maps = new HashMap<>();
        Map<Product, Integer> products = new HashMap<>();
        Integer size = 0;
        double sum = 0;
        for (Car car1 : cars) {
            System.out.println("更新后的car：" + car1);
            products.put(productMapper.getProduct(car1.getPid()), car1.getNum());
            sum += productMapper.getProduct(car1.getPid()).getShop_price() * car1.getNum();
            size += car1.getNum();
        }
        session.setAttribute("products", products);
        session.setAttribute("prices", sum);
        session.setAttribute("items", size);
        maps.put("sum", sum);
        maps.put("size", size);
        maps.put("state", true);
        return maps;
    }

    /**
     * 跳转到购物车
     *
     * @param session
     * @return
     */
    @GetMapping("/car")
    public String toCarPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ArrayList<Car> car = carMapper.getCar(user.getUid());
        Map<Product, Integer> products = new HashMap<>();
        for (Car car1 : car) {
            products.put(productMapper.getProduct(car1.getPid()), car1.getNum());
        }
        if (products.size() == 0) {
            session.setAttribute("cars", null);
        }
        session.setAttribute("cars", products);
        return "/car/car";
    }

    /**
     * 结算购物车
     *
     * @return
     */
    @PostMapping("/car/accounts")
    @ResponseBody
    public boolean carAccounts(@RequestBody Car[] cars, HttpSession session) {
        if (cars == null) {
            return false;
        }
        Map<Product, Integer> products = new HashMap<>();
        for (Car car : cars) {
            System.out.println(car);
            products.put(productMapper.getProduct(car.getPid()), car.getNum());
        }
        session.setAttribute("products", products);
        return true;
    }

    /**
     * 跳转结算页面
     */
    @GetMapping("/car/accounts")
    public String carToAccounts(HttpSession session, Map<String, Object> maps) {
        User user = (User) session.getAttribute("user");
        ArrayList<Address> addresses = addressMapper.selectAddress(user.getUid());
        maps.put("address", addresses);
        return "/order/order";
    }

}
