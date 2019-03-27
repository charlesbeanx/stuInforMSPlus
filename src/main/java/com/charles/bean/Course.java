package com.charles.bean;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("mims_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程相关
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer locked;
    private Integer del;
    private Date createdate;
    private String description;
    private Integer week;
    private Integer count;
    /**
     * 该课程授课老师的信息
     */
    private Integer teacherid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    @Override
    public String toString() {
        return "Course{" +
        ", id=" + id +
        ", name=" + name +
        ", locked=" + locked +
        ", del=" + del +
        ", createdate=" + createdate +
        ", description=" + description +
        ", week=" + week +
        ", count=" + count +
        ", teacherid=" + teacherid +
        "}";
    }
}
