package com.charles.service;

import com.charles.bean.Permission;
import com.baomidou.mybatisplus.service.IService;
import com.charles.bean.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> getUserLoginMenus(User loginUserUpdate);

    Boolean mims_delete(Integer id);
}
