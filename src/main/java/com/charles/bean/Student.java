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
 * @since 2019-03-06
 */
@TableName("mims_student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生的信息的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String stunumber;
    private String name;
    private String sex;
    private String birthday;
    private Integer cardnumber;
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
    private String qq;
    private String phone;
    private String photo;
    /**
     * 该学生的创建时间
     */
    private String createtime;
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber;
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

    public Integer getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(Integer cardnumber) {
        this.cardnumber = cardnumber;
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

    @Override
    public String toString() {
        return "Student{" +
        ", id=" + id +
        ", stunumber=" + stunumber +
        ", name=" + name +
        ", sex=" + sex +
        ", birthday=" + birthday +
        ", cardnumber=" + cardnumber +
        ", education=" + education +
        ", gradeid=" + gradeid +
        ", locked=" + locked +
        ", del=" + del +
        ", qq=" + qq +
        ", phone=" + phone +
        ", photo=" + photo +
        ", createtime=" + createtime +
        ", email=" + email +
        "}";
    }
}
