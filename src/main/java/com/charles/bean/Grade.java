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
@TableName("mims_grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级信息的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer locked;
    private Integer del;
    private String description;
    private Date createdate;
    /**
     * 统计一个班级的人数
     */
    private Integer count;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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
        return "Grade{" +
        ", id=" + id +
        ", name=" + name +
        ", locked=" + locked +
        ", del=" + del +
        ", description=" + description +
        ", createdate=" + createdate +
        ", count=" + count +
        ", teacherid=" + teacherid +
        "}";
    }
}
