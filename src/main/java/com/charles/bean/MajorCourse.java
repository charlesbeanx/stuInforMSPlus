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
@TableName("mims_major_course")
public class MajorCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer mimsMajId;
    private Integer mimsCouId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMimsMajId() {
        return mimsMajId;
    }

    public void setMimsMajId(Integer mimsMajId) {
        this.mimsMajId = mimsMajId;
    }

    public Integer getMimsCouId() {
        return mimsCouId;
    }

    public void setMimsCouId(Integer mimsCouId) {
        this.mimsCouId = mimsCouId;
    }

    @Override
    public String toString() {
        return "MajorCourse{" +
        ", id=" + id +
        ", mimsMajId=" + mimsMajId +
        ", mimsCouId=" + mimsCouId +
        "}";
    }
}
