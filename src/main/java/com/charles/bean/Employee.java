package com.charles.bean;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
@TableName("mims_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工列表的信息唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String number;
    private String name;
    private Integer locked;
    private Integer del;
    private String description;
    private String sex;
    private String email;
    private String qq;
    private String phone;
    private String photo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Employee{" +
        ", id=" + id +
        ", number=" + number +
        ", name=" + name +
        ", locked=" + locked +
        ", del=" + del +
        ", description=" + description +
        ", sex=" + sex +
        ", email=" + email +
        ", qq=" + qq +
        ", phone=" + phone +
        ", photo=" + photo +
        "}";
    }
}
