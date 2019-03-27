package com.charles.mapper;

import com.charles.bean.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getUserLoginMenus(Integer id);
}
