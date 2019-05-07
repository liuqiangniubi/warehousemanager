package com.four.masscommercialcity.bean;

/**
 * 订单详情类
 */
public class Orderitem {
    private Integer itemid;//订单项id
    private Integer count;//数量
    private Integer subtotal;//用户ID
    private Integer pid;//状态
    private Integer oid;//订单id

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "orderitem [itemid=" + itemid + ", count=" + count + ", subtotal=" + subtotal + ", pid=" + pid + ", oid="
                + oid + "]";
    }
}
