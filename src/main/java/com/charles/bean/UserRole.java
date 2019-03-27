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
@TableName("mims_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关于用户和角色之间的相互关系的唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 显示对应的角色的唯一标识符
     */
    private Integer mimsRoleId;
    /**
     * 用户的唯一标识符
     */
    private Integer mimsUserId;

    public UserRole() {
    }

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

    public Integer getMimsUserId() {
        return mimsUserId;
    }

    public void setMimsUserId(Integer mimsUserId) {
        this.mimsUserId = mimsUserId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        ", id=" + id +
        ", mimsRoleId=" + mimsRoleId +
        ", mimsUserId=" + mimsUserId +
        "}";
    }
}
