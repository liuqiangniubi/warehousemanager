package com.four.masscommercialcity.bean;

import java.util.Objects;

/**
 * 分类
 */
public class Category {
    private Integer cid;//分类id
    private String cname;//分类名字

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "category [cid=" + cid + ", cname=" + cname + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(cid, category.cid) &&
                Objects.equals(cname, category.cname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, cname);
    }
}
