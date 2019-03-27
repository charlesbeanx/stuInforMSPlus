package com.charles.bean;

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
@TableName("mims_grade_course")
public class GradeCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer minsCourseId;
    private Integer minsGradeId;

    public GradeCourse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinsCourseId() {
        return minsCourseId;
    }

    public void setMinsCourseId(Integer minsCourseId) {
        this.minsCourseId = minsCourseId;
    }

    public Integer getMinsGradeId() {
        return minsGradeId;
    }

    public void setMinsGradeId(Integer minsGradeId) {
        this.minsGradeId = minsGradeId;
    }

    @Override
    public String toString() {
        return "GradeCourse{" +
        ", id=" + id +
        ", minsCourseId=" + minsCourseId +
        ", minsGradeId=" + minsGradeId +
        "}";
    }
}
