package com.charles.service;

import com.charles.bean.Role;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
public interface IRoleService extends IService<Role> {

    Boolean mims_perRoleUpdate(Integer[] rids, Role role);

    Integer perRoleDelete(Integer roleId);

    boolean perRoleAdd(Role role, Integer[] rids);
}
