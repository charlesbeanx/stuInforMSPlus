package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.Permission;
import com.charles.bean.Role;
import com.charles.bean.RolePermission;
import com.charles.bean.User;
import com.charles.mapper.PermissionMapper;
import com.charles.mapper.RolePermissionMapper;
import com.charles.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Permission> getUserLoginMenus(User loginUserUpdate) {
        return permissionMapper.getUserLoginMenus(loginUserUpdate.getId());
    }

    @Override
    public Boolean mims_delete(Integer id) {
        //应该先删除的是中间表的信息
        rolePermissionMapper.delete(new EntityWrapper<RolePermission>().eq("mims_permission_id",id));
        //再直接删除资源
        Integer integer = permissionMapper.deleteById(id);
        //删除资源成功之后删除中间表中的信息
        if (integer>0){
            return true;
        }
        return false;
    }
}
