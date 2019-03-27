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
@TableName("mims_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所有的权限信息
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 权限的名字
     */
    private String name;
    /**
     * 权限的图标
     */
    private String icon;
    /**
     * 权限的类型，是权限（permission）还是目录（menu）
     */
    private String type;
    /**
     * 目录问题
     */
    private String url;
    /**
     * 如果是目录，父级目录是什么
     */
    private Integer parentid;
    /**
     * 父级目录的全路径
     */
    private String parentids;
    /**
     * 该权限或者是目录是否已经锁定：0表示猥琐的；1表示锁定
     */
    private Integer locked;
    private String sortstring;
    private String percode;

    @TableField(exist = false)
    private boolean flag;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", parentid=" + parentid +
                ", parentids='" + parentids + '\'' +
                ", locked=" + locked +
                ", sortstring='" + sortstring + '\'' +
                ", percode='" + percode + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Permission() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

}
