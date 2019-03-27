package com.charles.bean;

import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("mims_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门表的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private String createtime;
    private Integer locked;
    private Integer del;
    private String description;
    @TableField(exist = false)
    private Integer empCount;
    @TableField(exist = false)
    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Department() {
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createtime='" + createtime + '\'' +
                ", locked=" + locked +
                ", del=" + del +
                ", description='" + description + '\'' +
                ", empCount=" + empCount +
                ", flag=" + flag +
                '}';
    }
}
