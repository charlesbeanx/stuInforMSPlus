package com.charles.bean;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@TableName("mims_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生的编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户的登录名
     */
    private String username;
    /**
     * 用户的登录密码
     */
    private String password;
    /**
     * 用户的使用禁用状态：0表示使用状态  1表示禁用状态 （这是管理员的操作）
     */
    private Integer locked;
    /**
     * 用户的删除状态：0表示未删除 1表示删除（这个是用户的操作）
     */
    private Integer del;
    /**
     * 用户的头像
     */
    private String photo;
    /**
     * 用户当前登录的时间
     */
    @TableField("currentTime")
    private String currentTime;
    /**
     * 用户当前登录的ip地址
     */
    private String ip;
    @TableField("lastTime")
    private String lastTime;
    /**
     * 用户的自我介绍
     */
    private String description;
    @TableField("online")
    private Integer online;
    @TableField(exist =false)
    private List<Role> roles;



    public User() {
    }

    public User(String username, String password, Integer locked, Integer del, String photo, String currentTime, String ip, String lastTime, String description, Integer online) {
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.del = del;
        this.photo = photo;
        this.currentTime = currentTime;
        this.ip = ip;
        this.lastTime = lastTime;
        this.description = description;
        this.online = online;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "User{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", locked=" + locked +
        ", del=" + del +
        ", photo=" + photo +
        ", currentTime=" + currentTime +
        ", ip=" + ip +
        ", lastTime=" + lastTime +
        ", description=" + description +
        "，online="+online+"}";
    }
}
