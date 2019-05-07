package com.four.masscommercialcity.bean;

import java.util.Date;

/**
 * 用户类
 */
public class User {
    private Integer uid;//用户id
    private String username;//昵称
    private String password;//密码
    private String name;//真实姓名
    private String email;//邮箱
    private String telephone;//电话
    private Date birthday;//生日
    private Integer sex;//性别
    private Integer state;//状态
    private String code;//验证码

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "user [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
                + email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", state=" + state
                + ", code=" + code + "]";
    }
}
