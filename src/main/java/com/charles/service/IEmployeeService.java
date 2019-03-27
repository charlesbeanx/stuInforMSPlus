package com.charles.service;

import com.charles.bean.Employee;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
public interface IEmployeeService extends IService<Employee> {

    Boolean mims_delete(Integer id);

    Boolean mims_add(Employee employee, Integer[] ids);

    Boolean mims_update(Employee employee, Integer[] ids);
}
