package com.charles.service;

import com.charles.bean.Department;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
public interface IDepartmentService extends IService<Department> {

    Integer mims_delete(Integer id);
}
