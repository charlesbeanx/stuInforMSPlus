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
 * @since 2019-03-06
 */
@TableName("mims_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色信息表的唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    /**
     * 该角色的使用与禁用状态显示：0表示能使用;1表示禁用
     */
    private Integer locked;
    /**
     * 该角色的简单信息描述
     */
    private String description;
    @TableField(exist = false)
    private Integer flag;

    public Role(String name, Integer locked, String description) {
        this.name = name;
        this.locked = locked;
        this.description = description;
    }

    public Integer getflag() {
        return flag;
    }

    public void setflag(Integer flag) {
        this.flag = flag;
    }

    public Role() {
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

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", name=" + name +
        ", locked=" + locked +
        ", description=" + description +
        "}";
    }
}
