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
 * @since 2019-03-27
 */
@TableName("mims_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生的信息的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String number;
    private String name;
    private String sex;
    private String birthday;
    private Integer idcar;
    private String education;
    private Integer gradeid;
    /**
     * 学生的相关使用和禁用状态：0表示能使用；1表示禁用
     */
    private Integer locked;
    /**
     * 学生的删状态：0表示未删除；1表示已删除
     */
    private Integer del;
    private String phone;
    private String photo;
    /**
     * 该学生的创建时间
     */
    private String createtime;
    private String email;
    private String qq;


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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getIdcar() {
        return idcar;
    }

    public void setIdcar(Integer idcar) {
        this.idcar = idcar;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getGradeid() {
        return gradeid;
    }

    public void setGradeid(Integer gradeid) {
        this.gradeid = gradeid;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    @Override
    public String toString() {
        return "Student{" +
        ", id=" + id +
        ", number=" + number +
        ", name=" + name +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", idcar=" + idcar +
        ", education=" + education +
        ", gradeid=" + gradeid +
        ", locked=" + locked +
        ", del=" + del +
        ", phone=" + phone +
        ", photo=" + photo +
        ", createtime=" + createtime +
        ", email=" + email +
        ", qq=" + qq +
        "}";
    }
}
