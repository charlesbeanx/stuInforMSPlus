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
 * @since 2019-03-06
 */
@TableName("mims_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色其对应的权限的相关信息的唯一标识符
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色信息的唯一标识符
     */
    private Integer mimsRoleId;
    /**
     * 权限信息的唯一标识符
     */
    private Integer mimsPermissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMimsRoleId() {
        return mimsRoleId;
    }

    public void setMimsRoleId(Integer mimsRoleId) {
        this.mimsRoleId = mimsRoleId;
    }

    public Integer getMimsPermissionId() {
        return mimsPermissionId;
    }

    public void setMimsPermissionId(Integer mimsPermissionId) {
        this.mimsPermissionId = mimsPermissionId;
    }



    @Override
    public String toString() {
        return "RolePermission{" +
        ", id=" + id +
        ", mimsRoleId=" + mimsRoleId +
        ", mimsPermissionId=" + mimsPermissionId +
        "}";
    }
}
