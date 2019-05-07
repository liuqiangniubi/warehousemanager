package com.four.masscommercialcity.bean;

import java.util.Date;
import java.util.Objects;

/**
 * 商品类
 */
public class Product {
    private Integer pid;//商品id
    private String pname;//商品名字
    private Double market_price;//市场价
    private Double Shop_price;//售价
    private String pimage;//商品照片
    private Integer num;//商品库存
    private Date pdate;//商品日期
    private Integer Is_hot;//是否热门
    private String pdesc;//商品描述
    private Integer pflag;//是否下架
    private Category category;//商品分类

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getShop_price() {
        return Shop_price;
    }

    public void setShop_price(Double shop_price) {
        Shop_price = shop_price;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Integer getIs_hot() {
        return Is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        Is_hot = is_hot;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public Integer getPflag() {
        return pflag;
    }

    public void setPflag(Integer pflag) {
        this.pflag = pflag;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(pid, product.pid) &&
                Objects.equals(pname, product.pname) &&
                Objects.equals(market_price, product.market_price) &&
                Objects.equals(Shop_price, product.Shop_price) &&
                Objects.equals(pimage, product.pimage) &&
                Objects.equals(num, product.num) &&
                Objects.equals(pdate, product.pdate) &&
                Objects.equals(Is_hot, product.Is_hot) &&
                Objects.equals(pdesc, product.pdesc) &&
                Objects.equals(pflag, product.pflag) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pid, pname, market_price, Shop_price, pimage, num, pdate, Is_hot, pdesc, pflag, category);
    }
}
