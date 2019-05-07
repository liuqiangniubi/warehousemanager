package com.four.masscommercialcity.bean;

/**
 * 购物车类
 */
public class Car {
    private Integer id;
    private Integer pid;
    private Integer uid;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", pid=" + pid +
                ", uid=" + uid +
                ", num=" + num +
                '}';
    }
}
