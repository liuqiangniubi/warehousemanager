package com.four.masscommercialcity.bean;

import java.util.Date;

/**
 * 订单类
 */
public class Orders {
    private Integer oid;//订单ID
    private Date ordertime;//订单时间
    private Double total;//总金额
    private Integer state;//订单状态
    private String address;//地址
    private String name;//用户真名
    private String telepho;//电话
    private Integer uid;//用户id
    private String orderNum;//订单号

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelepho() {
        return telepho;
    }

    public void setTelepho(String telepho) {
        this.telepho = telepho;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "orders [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state
                + ", address=" + address + ", name=" + name + ", telepho=" + telepho + ", uid=" + uid + "]";
    }
}
