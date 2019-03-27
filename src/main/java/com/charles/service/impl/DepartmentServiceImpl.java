package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.Department;
import com.charles.bean.EmpDepart;
import com.charles.mapper.DepartmentMapper;
import com.charles.mapper.EmpDepartMapper;
import com.charles.service.IDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
@Transactional
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmpDepartMapper empDepartMapper;
    @Override
    public Integer mims_delete(Integer id) {
        Department department = departmentMapper.selectById(id);
        List<EmpDepart> empDeparts = empDepartMapper.selectList(new EntityWrapper<EmpDepart>().eq("mims_depart_id", id));
        if (!empDeparts.equals("")){
            department.setDel(1);
            departmentMapper.updateById(department);
            return 1;
        }else {
            return 2;
        }
    }
}
