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
@TableName("mims_course_teacher")
public class CourseTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer mimsCouId;
    private Integer mimsTeaId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMimsCouId() {
        return mimsCouId;
    }

    public void setMimsCouId(Integer mimsCouId) {
        this.mimsCouId = mimsCouId;
    }

    public Integer getMimsTeaId() {
        return mimsTeaId;
    }

    public void setMimsTeaId(Integer mimsTeaId) {
        this.mimsTeaId = mimsTeaId;
    }

    @Override
    public String toString() {
        return "CourseTeacher{" +
        ", id=" + id +
        ", mimsCouId=" + mimsCouId +
        ", mimsTeaId=" + mimsTeaId +
        "}";
    }
}
