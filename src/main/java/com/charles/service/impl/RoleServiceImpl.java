package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.Role;
import com.charles.bean.RolePermission;
import com.charles.mapper.RoleMapper;
import com.charles.mapper.RolePermissionMapper;
import com.charles.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mchange.util.impl.FSMessageLoggerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@Transactional
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Override
    public Boolean mims_perRoleUpdate(Integer[] rids, Role role) {
        //1 先更新角色信息
        roleMapper.updateById(role);
        //2 删除中间表的所有信息
        rolePermissionMapper.delete(new EntityWrapper<RolePermission>().eq("mims_role_id",role.getId()));
        //3 重新创建多对多联系
        RolePermission rolePermission = new RolePermission();
        for (Integer rid : rids) {
            rolePermission.setMimsRoleId(role.getId());
            rolePermission.setMimsPermissionId(rid);
            rolePermissionMapper.insert(rolePermission);
        }
        return true;
    }

    @Override
    public Integer perRoleDelete(Integer roleId) {
        //1 删除该角色的所有目录和权限
        rolePermissionMapper.delete(new EntityWrapper<RolePermission>().eq("mims_role_id",roleId));
        //2 删除该角色(真删除)
        Integer delete = roleMapper.delete(new EntityWrapper<Role>().eq("id", roleId));
//        Integer integer = roleMapper.deleteById(roleId);
        return delete;
    }

    @Override
    public boolean perRoleAdd(Role role, Integer[] rids) {
        //先更新角色信息
        Integer integer = roleMapper.insert(role);
        if (integer>0){
            RolePermission rolePermission = new RolePermission();
            for (Integer rid : rids) {
                rolePermission.setMimsRoleId(role.getId());
                rolePermission.setMimsPermissionId(rid);
                rolePermissionMapper.insert(rolePermission);
            }
            return true;
        }
        return false;
    }
}
