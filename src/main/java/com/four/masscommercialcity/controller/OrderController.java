package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.*;
import com.four.masscommercialcity.bean.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderitemMapper orderitemMapper;

    @Autowired
    CarMapper carMapper;

    @Autowired
    ProductMapper productMapper;

    /**
     * 查看所有订单
     *
     * @param maps
     * @return
     */
    @GetMapping(value = {"/order/allPage", "/order/allPage/{currentPage}"})
    public String allPage(Map<String, Object> maps,
                          @PathVariable(required = false, value = "currentPage") Integer currentPage
            , @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageHelper.startPage(currentPage, pageSize);
        ArrayList<Orders> orders = orderMapper.selectOrder();
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        maps.put("orders", orders);
        maps.put("pages", pageInfo);
        return "order/orderList";
    }

    /**
     * 生成订单
     *
     * @param session
     * @param aid
     * @return
     */
    @GetMapping("/car/pay/{aid}")
    @ResponseBody
    public boolean pay(HttpSession session, @PathVariable("aid") Integer aid) {
        boolean bl = true;
        double sum = 0;
        //获取商品，以及购物车内的商品数，以及总价
        Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute("products");

        //获取用户
        User user = (User) session.getAttribute("user");

        //  获取购物车中的商品
        Map<Product, Integer> cars = (Map<Product, Integer>) session.getAttribute("cars");

        //遍历商品，得出总价,并移除,并修改库存
        for (Product product : products.keySet()) {
            System.out.println("要删移除的商品：" + product);
            sum += product.getShop_price() * products.get(product);
            cars.remove(product);
            carMapper.delCar(user.getUid(), product.getPid());
            product.setNum(product.getNum() - products.get(product));
            productMapper.update(product);
        }

        Integer items = 0;
        double prices = 0;
        //遍历购物车
        for (Product product : cars.keySet()) {
            System.out.println("剩下的商品:" + product);
            items += cars.get(product);
            prices += product.getShop_price();
        }

        //存入session中
        session.setAttribute("items", items);
        session.setAttribute("prices", prices);

        //获取地址
        Address address = addressMapper.getAddress(aid);
        String a = address.getProvince() + address.getCity() + address.getDistrict() + address.getAddress();
        String phone = address.getPhone();

        //随机生成
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Random random = new Random();
        String str = simpleDateFormat.format(new Date());
        int num = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;

        //订单号
        String orderNum = num + str;
        System.out.println(orderNum);
        //创建订单对象
        Orders order = new Orders();
        order.setOrderNum(orderNum);
        order.setOrdertime(new Date());
        order.setTotal(sum);
        order.setState(0);
        order.setAddress(a);
        order.setName(address.getName());
        order.setTelepho(phone);
        order.setUid(user.getUid());
        //添加订单
        orderMapper.addOrder(order);

        //添加订单项
        for (Product product : products.keySet()) {
            Orderitem orderitem = new Orderitem();
            orderitem.setCount(products.get(product));
            orderitem.setOid(order.getOid());
            orderitem.setPid(product.getPid());
            orderitemMapper.addOrderitem(orderitem);
        }
        //将已下单的商品从购物车中清掉
        //保存地址id和总价,以及订单号
        session.setAttribute("addressId", aid);
        session.setAttribute("sums", sum);
        session.setAttribute("orderNum", order.getOrderNum());
        return bl;
    }

    /**
     * 跳转下单之后的页面
     *
     * @return
     */
    @GetMapping("/car/end")
    public String end(HttpSession session) {
        Integer addressId = (Integer) session.getAttribute("addressId");
        Address address = addressMapper.getAddress(addressId);
        session.setAttribute("adres", address);
        return "/commons/end";
    }

    /**
     * 查看用户订单订单
     *
     * @return
     */
    @GetMapping("/order")
    public String selectOrder(Map<String, Object> maps) {


        return "/order/orderList";
    }

    /**
     * 查看订单详情
     */
    @GetMapping("/order/orderItems/{oid}")
    public String allOrderItem(Map<String, Object> maps, @PathVariable(value = "oid") Integer oid) {
        Map<Product, Integer> products = new HashMap<>();
        ArrayList<Orderitem> orderitems = orderitemMapper.selectOrderItem(oid);
        for (Orderitem orderitem : orderitems) {
            products.put(productMapper.getProduct(orderitem.getPid()), orderitem.getCount());
        }
        maps.put("orderItems", products);
        return "/order/orderItem";
    }

}
