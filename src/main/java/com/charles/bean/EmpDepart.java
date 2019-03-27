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
 * @since 2019-03-22
 */
@TableName("mims_emp_depart")
public class EmpDepart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门和员工的关系的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 员工的id
     */
    private Integer mimsEmpId;
    /**
     * 部门的id
     */
    private Integer mimsDepartId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMimsEmpId() {
        return mimsEmpId;
    }

    public void setMimsEmpId(Integer mimsEmpId) {
        this.mimsEmpId = mimsEmpId;
    }

    public Integer getMimsDepartId() {
        return mimsDepartId;
    }

    public void setMimsDepartId(Integer mimsDepartId) {
        this.mimsDepartId = mimsDepartId;
    }

    @Override
    public String toString() {
        return "EmpDepart{" +
        ", id=" + id +
        ", mimsEmpId=" + mimsEmpId +
        ", mimsDepartId=" + mimsDepartId +
        "}";
    }
}
