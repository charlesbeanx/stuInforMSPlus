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
 * @since 2019-03-14
 */
@TableName("mims_loginlog")
public class Loginlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String ip;
    private String createtime;
    private String location;
    private Integer userid;

    public Loginlog(String ip, String createtime, String location, Integer userid) {
        this.ip = ip;
        this.createtime = createtime;
        this.location = location;
        this.userid = userid;
    }
    public Loginlog() {
    }

    public Loginlog(Integer id, String ip, String createtime, String location, Integer userid) {
        this.id = id;
        this.ip = ip;
        this.createtime = createtime;
        this.location = location;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Loginlog{" +
        ", id=" + id +
        ", ip=" + ip +
        ", createtime=" + createtime +
        ", location=" + location +
        ", userid=" + userid +
        "}";
    }
}
